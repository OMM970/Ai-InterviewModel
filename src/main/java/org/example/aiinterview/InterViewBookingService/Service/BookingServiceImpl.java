package org.example.aiinterview.InterViewBookingService.Service;

import org.example.aiinterview.InterViewBookingService.Dtos.booking_RequestDto;
import org.example.aiinterview.InterViewBookingService.Dtos.booking_ResponseDto;
import org.example.aiinterview.InterViewBookingService.Entitiy.BookingEntity;

public interface BookingServiceImpl {
    booking_ResponseDto maptoDto(BookingEntity bookingEntity,String interviewId
                                 );

    booking_ResponseDto createMetting(booking_RequestDto bookingRequestDto);
}
