package com.hackathon.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenUtils {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Map<String, String> tokenToOriginal = new ConcurrentHashMap<>();
    private static final Map<String, String> originalToToken = new ConcurrentHashMap<>();

    private static final byte[] aesKey = generateAesKey();
    private static final byte[] hmacKey = generateHmacKey();

    public enum TokenType {
        NUMERIC, STRING
    }

    public static String tokenize(String original, int length, TokenType tokenType) {
        try {
            // Encrypt original value (AES-GCM + random IV)
            byte[] iv = new byte[12];
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            SecretKey aesSecret = new SecretKeySpec(aesKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesSecret, new GCMParameterSpec(128, iv));
            byte[] encrypted = cipher.doFinal(original.getBytes());

            // HMAC for secure, irreversible token ID
            Mac hmac = Mac.getInstance("HmacSHA256");
            hmac.init(new SecretKeySpec(hmacKey, "HmacSHA256"));
            byte[] hmacDigest = hmac.doFinal(encrypted);

            // Convert to appropriate format
            String token;
            switch (tokenType) {
                case NUMERIC:
                    token = generateNumericToken(hmacDigest, length);
                    break;
                case STRING:
                default:
                    token = generateAlphaToken(hmacDigest, length);
            }

            tokenToOriginal.put(token, original);
            originalToToken.put(original, token);

            return token;

        } catch (Exception e) {
            throw new RuntimeException("Tokenization failed", e);
        }
    }

    public static String detokenize(String token) {
        return tokenToOriginal.getOrDefault(token, null);
    }

    private static String generateNumericToken(byte[] bytes, int length) {
        BigInteger bigInt = new BigInteger(1, bytes);
        String numeric = bigInt.toString(10);
        return numeric.substring(0, Math.min(length, numeric.length()));
    }

    private static String generateAlphaToken(byte[] bytes, int length) {
        String base62 = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        return base62.replaceAll("[^A-Za-z]", "").substring(0, Math.min(length, base62.length()));
    }

    private static byte[] generateAesKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            return keyGen.generateKey().getEncoded();
        } catch (Exception e) {
            throw new RuntimeException("AES key generation failed", e);
        }
    }

    private static byte[] generateHmacKey() {
        byte[] key = new byte[32]; // 256-bit key
        secureRandom.nextBytes(key);
        return key;
    }
}

