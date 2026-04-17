package org.example.aiinterview.InterViewBookingService.Service;

import lombok.RequiredArgsConstructor;
import org.example.aiinterview.InterViewBookingService.Dtos.booking_RequestDto;
import org.example.aiinterview.InterViewBookingService.Dtos.booking_ResponseDto;
import org.example.aiinterview.InterViewBookingService.Entitiy.BookingEntity;
import org.example.aiinterview.InterViewBookingService.Enums.Interview_Status;
import org.example.aiinterview.InterViewBookingService.Enums.Interview_domain;
import org.example.aiinterview.InterViewBookingService.Repository.BookingRepositroy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService implements BookingServiceImpl {
    private final BookingRepositroy bookingRepositroy;


    @Override
    public booking_ResponseDto maptoDto(BookingEntity bookingEntity) {
        booking_ResponseDto responseDto = booking_ResponseDto.builder()
                .id(bookingEntity.getId())
                .fullName(bookingEntity.getFullName())
                .email(bookingEntity.getEmail())
                .experience_Level(bookingEntity.getExperience_Level())
                .interview_Domain(bookingEntity.getInterview_Domain())
                .interviewDateTime(bookingEntity.getInterviewDateTime())
                .others_Domain(bookingEntity.getOthers_Domain())
                .admin_notes(bookingEntity.getAdmin_notes())
                .build();
        return responseDto;

    }

    @Override
    public booking_ResponseDto createMetting(booking_RequestDto bookingRequestDto) {

        BookingEntity bookingEntity = BookingEntity.builder()
                .fullName(bookingRequestDto.getFullName())
                .email(bookingRequestDto.getEmail())
                .experience_Level(bookingRequestDto.getExperience_Level())
                .interviewDateTime(bookingRequestDto.getInterviewDateTime())
                .admin_notes(bookingRequestDto.getAdmin_notes())
                .interview_Status(Interview_Status.SCHEDULED)
                .build();

        if (bookingRequestDto.getInterview_Domain() == Interview_domain.OTHERS
                && bookingRequestDto.getOthers_Domain() != null
                && !bookingRequestDto.getOthers_Domain().trim().isEmpty()) {

            bookingEntity.setInterview_Domain(Interview_domain.OTHERS);
            bookingEntity.setOthers_Domain(bookingRequestDto.getOthers_Domain());

        } else {

            bookingEntity.setInterview_Domain(bookingRequestDto.getInterview_Domain());
            bookingEntity.setOthers_Domain(null);
        }

        bookingEntity = bookingRepositroy.save(bookingEntity);

        return maptoDto(bookingEntity);
    }


}
