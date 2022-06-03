package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import online.inote.naruto.utils.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("67236618ad696de2a91700b1afda43d0" + "1" + "/get");
        if (!Objects.isNull("1")) {
            sb.append("1");
        }

        if (!Objects.isNull("pig")) {
            sb.append("pig");
        }

        String jsonStr = "{\n" +
                "\t\"data\":\"city\"\n" +
                "}";

        //转换成为JSONObject对象
        //JSONObject jsonObj =Js
        sb.append(argumentsSort(jsonStr));
        String s = DigestUtils.md5DigestAsHex(sb.toString().getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
    }

    private static String argumentsSort(Object... arguments) {
        if (arguments != null && arguments.length > 0) {
            List<Object> list = (List) Arrays.stream(arguments).sorted(Comparator.comparing(Object::hashCode)).collect(Collectors.toList());
            char[] chars = JSON.toJSONString(list).toCharArray();
            Arrays.sort(chars);
            return Arrays.toString(chars);
        } else {
            return "";
        }
    }
}
