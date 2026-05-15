package org.example.aiinterview.InterViewBookingService.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
@RequiredArgsConstructor
@Service
@Slf4j
public class CredentialGeneratorService {
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$";

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateInterviewId() {
        return "TSH-" + new BigInteger(50, RANDOM)
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

    public  void saveInterviewId(String interviewId, String hashedPassword,
                                LocalDateTime expiryTime) {
        String redisKey = "interview:auth:" + interviewId;

        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

        hashOperations.put(redisKey, "interviewId", interviewId);
        hashOperations.put(redisKey, "passwordHash", hashedPassword);
        hashOperations.put(redisKey, "expiryTime", expiryTime.toString());
        hashOperations.put(redisKey, "status", "ACTIVE");

        Duration ttl=Duration.between(LocalDateTime.now(),expiryTime);
        log.info("Redis TTL in seconds: {}", ttl.getSeconds());

        redisTemplate.expire(redisKey, ttl);
    }
}
