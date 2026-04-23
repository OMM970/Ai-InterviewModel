package org.example.aiinterview.InterViewBookingService.Listner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.aiinterview.InterViewBookingService.Dtos.MailDto;
import org.example.aiinterview.InterViewBookingService.Service.MailService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailListner {

    private final MailService mailService;

    @Async
    @EventListener
    public void handleMailEvent(MailDto mailDto) {

        try {
            mailService.sendMail(mailDto);

        } catch (Exception e) {
            log.error("Failed to send email for ID: {}", mailDto.getInterviewId(), e);
        }
    }
}