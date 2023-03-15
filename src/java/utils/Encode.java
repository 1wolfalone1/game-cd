/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

public class Encode {
    //md5
    //sha-1 thương duoc sai

    public static String toSHA1(String txt) {
        String salt = "asfalkrjweoi;dsf";
        txt += salt;
        String result = null;
        try {
            byte[] dataBytes = txt.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(toSHA1("123456"));
    }

    public static String ranToken() {
        String token = String.valueOf(Math.random() * 100 * System.currentTimeMillis() * Math.random());
        token = Encode.toSHA1(token);
        return token;
    }
}
