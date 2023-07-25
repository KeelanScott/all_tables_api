package org.kainos.ea.encryption;

import org.kainos.ea.exception.FailedToEncryptTokenException;

public class TokenEncryption {
    public static String encryptToken(String token, String key) throws FailedToEncryptTokenException {
        StringBuilder encryptedToken = new StringBuilder();

        try {
        for (int i = 0; i < token.length(); i++) {
            char currentChar = token.charAt(i);
            char currentKeyChar = key.charAt(i % key.length());

            if (Character.isLetter(currentChar)) {
                char shiftBase = Character.isUpperCase(currentChar) ? 'A' : 'a';
                char encryptedChar = (char) ((currentChar - shiftBase + currentKeyChar - 'A') % 26 + shiftBase);
                encryptedToken.append(encryptedChar);
            } else {
                encryptedToken.append(currentChar);
            }
        }
        } catch (Exception e) {
            throw new FailedToEncryptTokenException();
        }

        return encryptedToken.toString();
    }
}