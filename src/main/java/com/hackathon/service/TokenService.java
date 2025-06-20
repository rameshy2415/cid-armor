package com.hackathon.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TokenService {

    private static final String CHARACTERS = "0123456789";
    private static final int TOKEN_LENGTH = 6;
    private final Random random = new Random();

    public String generateToken() {
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            token.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return token.toString();
    }

    public String generateUniqueToken() {
        return System.currentTimeMillis() + generateToken();
    }
}
