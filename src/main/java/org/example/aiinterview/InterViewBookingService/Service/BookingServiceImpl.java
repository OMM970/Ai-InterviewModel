package org.example.aiinterview.InterViewBookingService.Service;

import org.example.aiinterview.InterViewBookingService.Dtos.booking_RequestDto;
import org.example.aiinterview.InterViewBookingService.Dtos.booking_ResponseDto;
import org.example.aiinterview.InterViewBookingService.Entitiy.BookingEntity;
import org.example.aiinterview.InterViewBookingService.Entitiy.ResumeTextEntity;

public interface BookingServiceImpl {
    booking_ResponseDto maptoDto(BookingEntity bookingEntity,String interviewId,ResumeTextEntity resumeTextEntity
                                );

    booking_ResponseDto createMetting(booking_RequestDto bookingRequestDto);
}
