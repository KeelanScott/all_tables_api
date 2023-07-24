//package org.kainos.ea.encryption;
//import org.kainos.ea.exception.FailedToEncryptTokenException;
////
//////import javax.crypto.Cipher;
//////import javax.crypto.SecretKey;
//////import javax.crypto.SecretKeyFactory;
//////import javax.crypto.spec.PBEKeySpec;
//////import javax.crypto.spec.SecretKeySpec;
//////import java.security.SecureRandom;
//////import java.util.Base64;
//////
//////public class TokenEncryption {
//////
//////    private static final int ITERATIONS = 65536;
//////    private static final int KEY_LENGTH = 128; // AES-128
//////
//////    public static String encryptToken(String token, String key) throws FailedToEncryptTokenException {
//////        // Generate a random salt
//////        byte[] salt = new byte[16];
//////        SecureRandom secureRandom = new SecureRandom();
//////        secureRandom.nextBytes(salt);
//////
//////        // Derive the secret key from the token and salt
//////        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//////        PBEKeySpec spec = new PBEKeySpec(token.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
//////        SecretKey secretKey = factory.generateSecret(spec);
//////        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
//////
//////        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//////        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
//////        byte[] encryptedBytes = cipher.doFinal(token.getBytes());
//////        return Base64.getEncoder().encodeToString(encryptedBytes);
//////    }
////
////
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.PBEKeySpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.security.SecureRandom;
//import java.util.Base64;
//
//    public class TokenEncryption {
//
//        private static final int ITERATIONS = 65536;
//        private static final int KEY_LENGTH = 128;
//
//        public static String encryptToken(String token, String key) throws FailedToEncryptTokenException {
//            // Get the environment variable for the secret key or use a default key
//            String secretKey = key != null ? key : "defaultSecretKey";
//
//            // Generate a random salt
//            byte[] salt = new byte[16];
//            SecureRandom secureRandom = new SecureRandom();
//            secureRandom.nextBytes(salt);
//
//            // Derive the secret key from the provided key and the salt
//            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//            PBEKeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
//            SecretKey derivedKey = factory.generateSecret(spec);
//            SecretKeySpec secretKeySpec = new SecretKeySpec(derivedKey.getEncoded(), "AES");
//
//            // Encrypt the token
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
//            byte[] encryptedBytes = cipher.doFinal(token.getBytes());
//
//            // Combine the salt and encrypted token and return as a Base64-encoded string
//            byte[] combinedBytes = new byte[salt.length + encryptedBytes.length];
//            System.arraycopy(salt, 0, combinedBytes, 0, salt.length);
//            System.arraycopy(encryptedBytes, 0, combinedBytes, salt.length, encryptedBytes.length);
//            return Base64.getEncoder().encodeToString(combinedBytes);
//        }
//
//
//
//        public static String decryptToken(String encryptedToken, String key) throws Exception {
//            // Get the environment variable for the secret key or use a default key
//            String secretKey = key != null ? key : "defaultSecretKey";
//
//            // Decode the Base64-encoded encrypted token
//            byte[] combinedBytes = Base64.getDecoder().decode(encryptedToken);
//
//            // Extract the salt and encrypted token
//            byte[] salt = new byte[16];
//            byte[] encryptedBytes = new byte[combinedBytes.length - salt.length];
//            System.arraycopy(combinedBytes, 0, salt, 0, salt.length);
//            System.arraycopy(combinedBytes, salt.length, encryptedBytes, 0, encryptedBytes.length);
//
//            // Derive the secret key from the provided key and the salt
//            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//            PBEKeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
//            SecretKey derivedKey = factory.generateSecret(spec);
//            SecretKeySpec secretKeySpec = new SecretKeySpec(derivedKey.getEncoded(), "AES");
//
//            // Decrypt the token
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
//            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
//            return new String(decryptedBytes);
//        }
//    }
//
//
//}

