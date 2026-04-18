package org.example.aiinterview.InterViewBookingService.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

public class CredentialGeneratorService {
    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$";

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateInterviewId() {
        return "INT_" + new BigInteger(50, RANDOM)
                .toString(32)
                .toUpperCase();
    }
    public static String generatePAssword() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));

        }
        return sb.toString();
    }
}
