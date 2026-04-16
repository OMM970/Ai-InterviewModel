package org.example.aiinterview.InterViewBookingService.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aiinterview.InterViewBookingService.Enums.Experience_Levels;
import org.example.aiinterview.InterViewBookingService.Enums.Interview_domain;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class booking_ResponseDto {
    private long id;
    private String fullName;
    private String email;
    private Interview_domain interview_Domain;
    private String others_Domain;
    private Experience_Levels experience_Level;
    private LocalDateTime interviewDateTime;
    private String admin_notes;

}
