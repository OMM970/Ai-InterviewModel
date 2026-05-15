package org.example.aiinterview.ResumeReadingModel.ModelService;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ResumeParseService {

    public String extractText(MultipartFile file) {

        try (PDDocument document =
                     Loader.loadPDF(file.getBytes())) {

            PDFTextStripper stripper =
                    new PDFTextStripper();

            return stripper.getText(document);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to parse PDF", e
            );
        }
    }
}
