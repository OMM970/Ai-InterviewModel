package org.example.aiinterview.ResumeReadingModel.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeAnalysis {

    private List<String> focusAreas;

    private String experienceLevel;

    private String questionDifficulty;

    private List<String> strengths;

    private List<String> suggestedTopics;
}
