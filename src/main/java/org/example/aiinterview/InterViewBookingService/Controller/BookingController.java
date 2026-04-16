package org.example.aiinterview.InterViewBookingService.Controller;

import lombok.RequiredArgsConstructor;
import org.example.aiinterview.InterViewBookingService.Dtos.booking_RequestDto;
import org.example.aiinterview.InterViewBookingService.Dtos.booking_ResponseDto;
import org.example.aiinterview.InterViewBookingService.Service.BookingService;
import org.example.aiinterview.InterViewBookingService.Service.BookingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule/interview")
@RequiredArgsConstructor
public class BookingController {

    private final BookingServiceImpl bookingService;

    @PostMapping
    public ResponseEntity<booking_ResponseDto> createMeeting(
            @RequestBody booking_RequestDto bookingRequestDto
    ) {

        booking_ResponseDto response = bookingService.createMetting(bookingRequestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
