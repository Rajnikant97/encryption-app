package com.honeywell.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AESEncryption {

    static SecretKey secretKey = null;
    static IvParameterSpec iv = generateIV();


    private SecretKey getSecretKey() throws Exception {
        if(secretKey != null) {
            return secretKey;
        }
//        return secretKey = generateKey();
        return secretKey = generateKeyFromUsername("Rajanikant");
    }
    // Generate a random AES Key
    private static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // AES-256
        return keyGen.generateKey();
    }

    // Generate a SecretKey from the username
    private static SecretKey generateKeyFromUsername(String username) throws Exception {
        byte[] salt = username.getBytes(); // Using username as salt (for demonstration)
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(username.toCharArray(), salt, 65536, 256);
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES");
    }

    private IvParameterSpec getIv() {
        if(iv != null) {
            return iv;
        }
        return iv = generateIV();
    }

    // Generate a random IV (Initialization Vector)
    private static IvParameterSpec generateIV() {
        byte[] iv = new byte[16]; // AES block size is 16 bytes
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    // Encrypt the message using AES
    private static String encrypt(String message, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Convert to Base64 string
    }

    // Decrypt the message using AES
    private static String decrypt(String encryptedMessage, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedBytes);
    }

    public String encrypt(String message) {
        try {
            // Generate key and IV
            SecretKey key = getSecretKey();
            IvParameterSpec iv = getIv();

            // Encrypt the message
            String encryptedMessage = encrypt(message, key, iv);
            System.out.println("Encrypted: " + encryptedMessage);

            // Decrypt the message
//            String decryptedMessage = decrypt(encryptedMessage, key, iv);
//            System.out.println("Decrypted: " + decryptedMessage);

            return encryptedMessage;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String decrypt(String cipherText) {
        try {
            // Generate key and IV
            SecretKey key = getSecretKey();
            IvParameterSpec iv = getIv();

            // Decrypt the message
            String decryptedMessage = decrypt(cipherText, key, iv);
            System.out.println("Decrypted: " + decryptedMessage);

            return decryptedMessage;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
