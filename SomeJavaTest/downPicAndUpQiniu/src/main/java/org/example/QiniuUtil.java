package org.example;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;

public  class QiniuUtil {
    /**
     * 认证对象
     */
    private static Auth auth=Auth.create("85Q7KC474TYdylErJF8xI5EzbusWUTsAOXGLqj_E", "kPqpMYqvDKm9kawzz_rJvVadGAFrm0Vk7r_iySgf");

    /**
     * 上传对象
     */
    private static UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone1()));
    /**
     * 空间名
     */
    private static String bucketName ="jykss";
    private static String outerLink="http://cdn.jykss.top";

    /**指定保存到七牛的文件名--同名上传会报错  {"error":"file exists"}*/
    /**
     * {"hash":"FrQF5eX_kNsNKwgGNeJ4TbBA0Xzr","key":"aa1.jpg"} 正常返回 key为七牛空间地址 http:/xxxx.com/aa1.jpg
     */

    private static String getUpToken() {
        return auth.uploadToken(bucketName);
    }

    /**
     * 上传七牛云
     *
     * @param file 本地文件
     * @param newFileName 七牛云新名字
     *
     * @return
     */
    public static String upload(File file, String newFileName) {
        try {

            // 1.调用put方法上传
            Response res = uploadManager.put(file, newFileName, getUpToken());
            // 2.解析返回
            if (res.statusCode == 200)
                // 在源地址后面加上“?attname=”，可实现点击链接即下载
                return outerLink + "/" + newFileName + "?attname=";

            return res.bodyString();
        } catch (QiniuException e) {
            System.out.println("上传七牛云失败");
        }
        return null;
    }

    /**
     * 上传七牛云
     *
     * @param file 本地文件
     *
     * @return
     */
    public String upload(File file) {
        return upload(file, file.getName());
    }

    /**
     * 上传七牛云
     *
     * @param localPath 本地文件路径
     *
     * @return
     */
    public String upload(String localPath) {
        return upload(new File(localPath));
    }
}
