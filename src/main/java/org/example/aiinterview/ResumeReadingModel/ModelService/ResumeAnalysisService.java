package org.example.aiinterview.ResumeReadingModel.ModelService;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ResumeAnalysisService {
    private final ChatClient chatClient;

    public String analyzeResume(String resumeText) {

        String prompt = """
        Analyze this resume and return:
        1. Key technical skills
        2. Experience level (Beginner/Intermediate/Advanced)
        3. Focus interview areas
        4. Suggested technical questions areas
        5. Candidate strengths

        Resume:
        %s
        """.formatted(resumeText);

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}
