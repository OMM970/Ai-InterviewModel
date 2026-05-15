package org.example.aiinterview.InterViewBookingService.Entitiy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "resumeTexts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResumeTextEntity {
    @Id
    private String id;

    private Long bookingId;

    private String resumeUrl;

    private String extractedText;

    private LocalDateTime uploadedAt;
}
