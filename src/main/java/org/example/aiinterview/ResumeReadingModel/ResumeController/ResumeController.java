//package org.example.aiinterview.ResumeReadingModel.ResumeController;
//
//import lombok.RequiredArgsConstructor;
//import org.example.aiinterview.ResumeReadingModel.ModelEntity.ParsedResume;
//import org.example.aiinterview.ResumeReadingModel.ModelEntity.ResumeAnalysis;
//import org.example.aiinterview.ResumeReadingModel.ModelEntity.ResumeEntity;
//import org.example.aiinterview.ResumeReadingModel.ModelRepo.ResumeRepository;
//import org.example.aiinterview.ResumeReadingModel.ModelService.ResumeAnalysisService;
//import org.example.aiinterview.ResumeReadingModel.ModelService.ResumeParseService;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/resume")
//@RequiredArgsConstructor
//public class ResumeController {
//
//    private final ResumeParseService parseService;
//    private final ResumeAnalysisService analysisService;
//    private final ResumeRepository resumeRepository;
//
//    @PostMapping("/upload")
//    public ResumeEntity uploadResume(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam String candidateName,
//            @RequestParam String email) {
//
//
//        String resumeText = parseService.extractText(file);
//
//
//        Map<String, Object> parsedData = analysisService.analyzeResume(resumeText);
//
//
//        List<String> skills =
//                (List<String>) parsedData.getOrDefault("skills", List.of());
//
//        List<Map<String, Object>> projects =
//                (List<Map<String, Object>>) parsedData.getOrDefault("projects", List.of());
//
//        List<Map<String, Object>> experienceList =
//                (List<Map<String, Object>>) parsedData.getOrDefault("experience", List.of());
//
//        List<String> focusAreas =
//                (List<String>) parsedData.getOrDefault("focusAreas", List.of());
//
//        List<String> topics =
//                (List<String>) parsedData.getOrDefault("suggestedTopics", List.of());
//
//        String experienceLevel =
//                (String) parsedData.getOrDefault("experienceLevel", "Unknown");
//
//
//        ParsedResume parsedResume = ParsedResume.builder()
//                .skills(skills)
//                .projects(projects)
//                .experience(experienceList)
//                .build();
//
//        // 5) Build Analysis
//        ResumeAnalysis analysis = ResumeAnalysis.builder()
//                .focusAreas(focusAreas)
//                .experienceLevel(experienceLevel)
//                .questionDifficulty("Medium")
//                .strengths(skills)
//                .suggestedTopics(topics)
//                .build();
//
//        // 6) Save to DB
//        ResumeEntity resume = ResumeEntity.builder()
//                .candidateName(candidateName)
//                .email(email)
//                .parsedResume(parsedResume)
//                .analysis(analysis)
//                .expiresAt(Instant.now().plus(24, ChronoUnit.HOURS))
//                .build();
//
//        return resumeRepository.save(resume);
//    }
//}