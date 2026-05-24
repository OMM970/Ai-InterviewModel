package org.example.aiinterview.ResumeReadingModel.ModelDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aiinterview.ResumeReadingModel.ModelEntity.ParsedResume;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeResponseDto {

    private String candidateName;

    private String email;

    private ParsedResume parsedResume;

    private String experienceLevel;

    private List<String> focusAreas;

    private List<String> strengths;

    private List<String> suggestedTopics;
}
