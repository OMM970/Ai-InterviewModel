package org.example.aiinterview.ResumeReadingModel.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParsedResume {

    private List<String> skills;

    private List<Map<String, Object>> projects;

    private List<Map<String, Object>> experience;
}
