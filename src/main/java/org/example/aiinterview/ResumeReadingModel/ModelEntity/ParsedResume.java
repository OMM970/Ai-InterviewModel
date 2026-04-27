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
public class ParsedResume {
    private String resumeText;

    private List<String> skills;
    private List<String> projects;
    private List<String> experience;
}
