package org.example.aiinterview.InterViewBookingService.Dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aiinterview.InterViewBookingService.Enums.Experience_Levels;
import org.example.aiinterview.InterViewBookingService.Enums.Interview_Status;
import org.example.aiinterview.InterViewBookingService.Enums.Interview_domain;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class booking_RequestDto {

    @NotBlank(message = "name cannot be blank")
    private String fullName;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$",message = "enter a valid email")
    @NotBlank(message = "mail cannot be blank")
    private String email;

    private Interview_domain interview_Domain;

    @NotBlank(message = "feild cannot be blank")
    private String others_Domain;

    private Experience_Levels experience_Level;

    private LocalDateTime interviewDateTime;


}
