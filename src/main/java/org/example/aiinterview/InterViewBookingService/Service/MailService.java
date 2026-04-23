package org.example.aiinterview.InterViewBookingService.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.aiinterview.InterViewBookingService.Dtos.MailDto;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;



@Service
@RequiredArgsConstructor
public class MailService {


    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendMail(MailDto dto) {

        Context context = new Context();
        context.setVariable("candidateName", dto.getName());
        context.setVariable("interviewDomain", dto.getDomain());
        context.setVariable("interviewDateTime", dto.getDateTime());
        context.setVariable("interviewId", dto.getInterviewId());
        context.setVariable("password", dto.getPassword());

        String html = templateEngine.process("Interview-Mail", context);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(dto.getEmail());
            helper.setSubject("Interview Scheduled - " + dto.getDomain());
            helper.setText(html, true);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
