package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("开始下载");
        String saveDirectory = "/Users/ykjiang/temp";
        downPicAndUpQiniu(saveDirectory);
        System.out.println("下载完成");
    }

    private static void downPicAndUpQiniu(String saveDirectory) throws Exception {
        File input = new File("src/main/resources/test.html");
        Document doc = Jsoup.parse(input, "UTF-8");

        Elements elements = doc.getAllElements();
        for (Element element : elements) {
            if (element.tagName().equalsIgnoreCase("img")) {
                String imageUrl = element.absUrl("src");
                try {
                    downloadImage(imageUrl, saveDirectory);
                } catch (Exception e) {
//                    throw new RuntimeException(e);
                    System.out.println(element);
                }
            }
        }
    }
    private static void downloadImage(String imageUrl, String saveDirectory) throws Exception {
        URL url = new URL(imageUrl);
        InputStream in = url.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        in.close();
        out.close();
        byte[] imageBytes = out.toByteArray();

        // 提取图片文件名
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1).replaceAll(".jpg","");

//        // 将图片保存到本地文件夹
        FileOutputStream fos = new FileOutputStream(saveDirectory + "/" + fileName);
        fos.write(imageBytes);
        fos.close();
        File file=new File(saveDirectory + "/" + fileName);
        QiniuUtil.upload(file, "picgo/"+fileName + ".png");
        file.delete();
    }

}



