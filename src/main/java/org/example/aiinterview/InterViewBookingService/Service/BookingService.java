package org.example.aiinterview.InterViewBookingService.Service;

import lombok.RequiredArgsConstructor;
import org.example.aiinterview.InterViewBookingService.Dtos.booking_ResponseDto;
import org.example.aiinterview.InterViewBookingService.Entitiy.BooingEntity;
import org.example.aiinterview.InterViewBookingService.Repository.BooingRepositroy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BooingRepositroy booingRepositroy;


    public booking_ResponseDto maptoDto(BooingEntity booingEntity) {
        booking_ResponseDto booking_ResponseDto = booking_ResponseDto.builder()
                .interviewDateTime(booingEntity.getInterviewDateTime())
                .id(booingEntity.getId())
                .email(booingEntity.getEmail())
                .interview_Domain(booingEntity.getInterview_Domain())
                .experience_Level(booingEntity.getExperience_Level())

    }
}
