package org.example.aiinterview.ResumeReadingModel.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "resumes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeEntity {
    @Id
    private String id;

    private String candidateName;
    private String email;

    private ParsedResume parsedResume;

    private ResumeAnalysis analysis;


    @Indexed(expireAfter = "0")
    private Instant expiresAt;

}
