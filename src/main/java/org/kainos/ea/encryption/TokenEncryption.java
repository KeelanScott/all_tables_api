package org.kainos.ea.encryption;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class TokenEncryption {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 128; // AES-128

    public static String encryptToken(String token) throws Exception {
        // Generate a random salt
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);

        // Derive the secret key from the token and salt
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec spec = new PBEKeySpec(token.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(token.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}

