package org.kainos.ea.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PasswordDecryption {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 128; // AES-128

    public static void main(String[] args) throws Exception {
        String encryptedPassword = "ENCRYPTED_PASSWORD"; // Replace this with the encrypted password from the database
        String saltString = "SALT"; // Replace this with the salt from the database (in Base64-encoded string)

        // Convert the salt from Base64 to bytes
        byte[] salt = Base64.getDecoder().decode(saltString);

        String password = "userPassword"; // Replace this with the user's password during login

        // Derive the secret key from the password and salt
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

        // Decrypt the password
        String decryptedPassword = decryptPassword(encryptedPassword, secretKeySpec);

        // Compare the entered password with the decrypted password
        if (password.equals(decryptedPassword)) {
            System.out.println("Password is correct.");
        } else {
            System.out.println("Password is incorrect.");
        }
    }

    private static String decryptPassword(String encryptedPassword, SecretKeySpec secretKeySpec) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}

