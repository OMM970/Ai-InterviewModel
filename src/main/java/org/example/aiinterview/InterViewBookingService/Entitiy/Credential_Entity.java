package org.example.aiinterview.InterViewBookingService.Entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "credentials")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Credential_Entity {

    @Id
    private String id;

    private String interviewId;

    private String passwordHash;

    private LocalDateTime createdAt;

    @Indexed(name = "credential_expiry_index", expireAfter = "0s")
    private LocalDateTime expiresAt;



}
