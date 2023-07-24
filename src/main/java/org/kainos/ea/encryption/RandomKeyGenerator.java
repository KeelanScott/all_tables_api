package org.kainos.ea.encryption;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomKeyGenerator {
    public static void main(String[] args) {
        int keyLength = 16; // You can adjust the length of the key as needed
        String randomKey = generateRandomKey(keyLength);
        System.out.println("Random Key: " + randomKey);
    }

    public static String generateRandomKey(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[length];
        secureRandom.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }
}
