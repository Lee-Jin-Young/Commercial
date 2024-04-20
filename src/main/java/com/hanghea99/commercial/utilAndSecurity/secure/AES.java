package com.hanghea99.commercial.utilAndSecurity.secure;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static String encryption(String text) {
        if (text.length() > 0) {
            String secretKey = "dbzpdj20@@";

            try {
                Cipher cipher = Cipher.getInstance("AES");

                byte[] key = new byte[16];
                int i = 0;

                for (byte b : secretKey.getBytes()) {
                    key[i++ % 16] ^= b;
                }

                cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));

                return new String(Hex.encodeHex(cipher.doFinal(text.getBytes("UTF-8")))).toUpperCase();
            } catch (Exception e) {
                return text;
            }
        } else {
            return null;
        }
    }

    public static String decryption(String encryptedText) {
        String secretKey = "dbzpdj20@@";
        try {
            Cipher cipher = Cipher.getInstance("AES");

            byte[] key = new byte[16];
            int i = 0;

            for (byte b : secretKey.getBytes()) {
                key[i++ % 16] ^= b;
            }

            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));

            return new String(cipher.doFinal(Hex.decodeHex(encryptedText.toCharArray())));
        } catch (Exception e) {
            return encryptedText;
        }
    }
}
