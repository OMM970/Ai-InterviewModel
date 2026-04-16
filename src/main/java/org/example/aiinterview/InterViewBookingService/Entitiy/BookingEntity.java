package org.example.aiinterview.InterViewBookingService.Entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aiinterview.InterViewBookingService.Enums.Experience_Levels;
import org.example.aiinterview.InterViewBookingService.Enums.Interview_Status;
import org.example.aiinterview.InterViewBookingService.Enums.Interview_domain;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interview_bookings")
public class BookingEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    @Enumerated(EnumType.STRING)
    private Interview_domain interview_Domain;

    private String others_Domain;

    @Enumerated(EnumType.STRING)
    private Experience_Levels experience_Level;

    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private Interview_Status  interview_Status;

    private LocalDateTime interviewDateTime;

    private String admin_notes;



}
