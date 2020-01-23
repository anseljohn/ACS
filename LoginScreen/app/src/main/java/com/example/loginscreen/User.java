package com.example.loginscreen;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class User {

    public String username;
    public String password;

    public User(String u, String p) {
        username = u;
        password = encrypt(u, p);
    }

    public static String encrypt(String u, String p) {
        try {
            String text = u;
            String key = p;
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            return new String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(User u) {
        try {
            String text = u.password;
            String key = u.username;
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            String decrypted = new String(cipher.doFinal(encrypted));
            return decrypted;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
