package org.example.aiinterview.ResumeReadingModel.ModelService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.example.aiinterview.ResumeReadingModel.ModelDto.ResumeResponseDto;
import org.example.aiinterview.ResumeReadingModel.ModelEntity.ParsedResume;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class ResumeAnalysisService {

    private final String GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";
    private final String API_KEY = "";

    public ResumeResponseDto analyzeResume(
            String resumeText
    ) {

        RestTemplate restTemplate = new RestTemplate();

        String prompt = """
You are an expert technical interviewer.

Analyze the resume and return ONLY JSON.

STRICT RULES:
- Do NOT hallucinate or introduce new technologies
- Use ONLY the information explicitly present in the resume
- If a skill/technology is not mentioned, DO NOT include it anywhere
- Do NOT assume knowledge of cloud, DevOps, or other domains unless explicitly written

DATA CONSISTENCY RULE:
- focusAreas and suggestedTopics MUST be derived ONLY from extracted skills and projects
- DO NOT introduce any new terms that are not present in skills/projects

FALLBACK RULE:
- If data is missing:
  - Use "Not Available" for strings
  - Use [] for arrays

Tasks:
1. Extract skills from resume
2. Extract projects with name and description
3. Extract experience details
4. Determine experienceLevel based ONLY on available data
5. Identify focusAreas ONLY from extracted skills
6. Generate suggestedTopics ONLY from skills and projects

Constraints:
- focusAreas must have at least 3 items (derive strictly from skills)
- suggestedTopics must have at least 5 items (strictly from skills/projects only)

Output format:
{
  "skills": [],
  "projects": [
    {
      "name": "",
      "description": ""
    }
  ],
  "experience": [
    {
      "role": "",
      "company": "",
      "details": ""
    }
  ],
  "experienceLevel": "",
  "focusAreas": [],
  "suggestedTopics": []
}

Return strictly valid JSON. No explanation.

Resume:
""" + resumeText;

        Map<String, Object> request = Map.of(
                "model", "llama-3.1-8b-instant",
                "messages", new Object[]{
                        Map.of("role", "user", "content", prompt)
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(GROQ_URL, entity, Map.class);

        Map choice = (Map) ((java.util.List) response.getBody().get("choices")).get(0);
        Map message = (Map) choice.get("message");

        ObjectMapper mapper = new ObjectMapper();


        try {
            String content = message.get("content").toString();


            content = content.replace("```json", "")
                    .replace("```", "")
                    .trim();


            int start = content.indexOf("{");
            int end = content.lastIndexOf("}");

            if (start != -1 && end != -1) {
                content = content.substring(start, end + 1);
            }

            return mapper.readValue(
                    content,
                    ResumeResponseDto.class
            );

        } catch (Exception e) {

            e.printStackTrace();

            return ResumeResponseDto.builder()

                    .candidateName("Not Available")

                    .email("Not Available")

                    .parsedResume(

                            ParsedResume.builder()

                                    .skills(List.of())

                                    .projects(List.of())

                                    .experience(List.of())

                                    .build()
                    )

                    .experienceLevel("Unknown")

                    .focusAreas(List.of())

                    .strengths(List.of())

                    .suggestedTopics(List.of())

                    .build();
        }
    }

   }
