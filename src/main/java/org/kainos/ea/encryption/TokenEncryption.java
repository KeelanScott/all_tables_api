package org.kainos.ea.encryption;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.spec.KeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TokenEncryption {

    // Create a function that takes in a token and key that encrypts the token
    // and returns the encrypted token
    // The key should be a random string of 16 characters
    // The token should be encrypted using AES encryption
// The token should be encrypted using the key and a salt

    public static String encryptToken(String token, String key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        byte[] salt = generateSalt();
        SecretKey secretKey = generateSecretKey(key, salt);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedToken = cipher.doFinal(token.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedToken);
    }


    private static byte[] generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }


}
