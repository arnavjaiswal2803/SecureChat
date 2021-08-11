package com.example.securechat;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtils {

    private final byte[] encryptionKey = {9, 115, 51, 86, 105, 4, -31, -23, -68, 88, 17, 20, 3, -105, 119, -53};
    private Cipher cipher, decipher;
    private SecretKeySpec secretKeySpec;

    public CryptoUtils() {
    }

    public void initializeEncryption() {
        try {
            cipher = Cipher.getInstance("AES");
            decipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        secretKeySpec = new SecretKeySpec(encryptionKey, "AES");
    }

    public String encryptUsingAES(String string) {
        byte[] stringByte = string.getBytes();
        byte[] encryptedByte;
        String encryptedString = null;

        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            encryptedByte = cipher.doFinal(stringByte);
            encryptedString = new String(encryptedByte, "ISO-8859-1");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encryptedString;
    }

    public String decryptString(String string) {
        byte[] encryptedByte;
        byte[] decryptedByte;
        String decryptedString = string;

        try {
            encryptedByte = string.getBytes("ISO-8859-1");
            decipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            decryptedByte = decipher.doFinal(encryptedByte);
            decryptedString = new String(decryptedByte);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return decryptedString;
    }
}
