package org.example.aiinterview.InterViewBookingService.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailDto {
    private String interviewId;
    private String email;
    private String name;
    private String domain;
    private String dateTime;
    private String password;
}
