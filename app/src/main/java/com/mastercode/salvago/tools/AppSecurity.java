package com.mastercode.salvago.tools;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppSecurity {


    public static String Encrypt(String value){
        String output = null;
        try{
            MessageDigest msg = MessageDigest.getInstance("MD5");
            byte[] bytes = msg.digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(byte b : bytes){ sb.append(String.format("%02x",b));}
            output = sb.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return output;
    }


}
