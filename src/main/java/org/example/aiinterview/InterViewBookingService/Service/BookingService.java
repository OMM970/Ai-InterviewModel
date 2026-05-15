package org.example.aiinterview.InterViewBookingService.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.aiinterview.InterViewBookingService.Dtos.MailDto;
import org.example.aiinterview.InterViewBookingService.Dtos.booking_RequestDto;
import org.example.aiinterview.InterViewBookingService.Dtos.booking_ResponseDto;
import org.example.aiinterview.InterViewBookingService.Entitiy.BookingEntity;
import org.example.aiinterview.InterViewBookingService.Entitiy.Credential_Entity;
import org.example.aiinterview.InterViewBookingService.Entitiy.ResumeTextEntity;
import org.example.aiinterview.InterViewBookingService.Enums.Interview_Status;
import org.example.aiinterview.InterViewBookingService.Enums.Interview_domain;
import org.example.aiinterview.InterViewBookingService.Repository.BookingRepositroy;
import org.example.aiinterview.InterViewBookingService.Repository.Credential_Repo;
import org.example.aiinterview.InterViewBookingService.Repository.ResumeTextRepo;
import org.example.aiinterview.ResumeReadingModel.ModelEntity.ResumeEntity;
import org.example.aiinterview.ResumeReadingModel.ModelService.ResumeAnalysisService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService implements BookingServiceImpl {
    private final BookingRepositroy bookingRepositroy;
    private final PasswordEncoder passwordEncoder;
    private final Credential_Repo credential_repo;
    private final CredentialGeneratorService credentialGeneratorService;
    private final ApplicationEventPublisher publisher;
    private final S3Service s3Service;
    private final ResumeAnalysisService resumeAnalysisService;
    private final ResumeTextRepo resumeTextRepo;


    @Override
    public booking_ResponseDto maptoDto(BookingEntity bookingEntity, String interviewId,ResumeTextEntity resumeTextEntity
    ) {
        booking_ResponseDto responseDto = booking_ResponseDto.builder()
                .id(bookingEntity.getId())
                .fullName(bookingEntity.getFullName())
                .email(bookingEntity.getEmail())
                .experience_Level(bookingEntity.getExperience_Level())
                .interview_Domain(bookingEntity.getInterview_Domain())
                .interviewDateTime(bookingEntity.getInterviewDateTime())
                .others_Domain(bookingEntity.getOthers_Domain())
                .admin_notes(bookingEntity.getAdmin_notes())
                .userID(interviewId)
                .extracted_TextId(resumeTextEntity.getId())
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
                .difficultyLevel(bookingRequestDto.getDifficultyLevel())
                .isActive(true)
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

        String interviewId = CredentialGeneratorService.generateInterviewId();
        String rawPassword = CredentialGeneratorService.generatePAssword();
        String hashedPassword = passwordEncoder.encode(rawPassword);

        Credential_Entity credentialEntity = new Credential_Entity();
        credentialEntity.setInterviewId(interviewId);
        credentialEntity.setPasswordHash(hashedPassword);
        credentialEntity.setCreatedAt(LocalDateTime.now());
        LocalDateTime expiryTime = bookingRequestDto.getInterviewDateTime().plusHours(24);

        credentialGeneratorService.saveInterviewId(interviewId, hashedPassword, expiryTime);
        log.info("Credential saved to Redis Sucessfull" + interviewId);


        credentialEntity.setExpiresAt(
                bookingRequestDto.getInterviewDateTime()
                        .toLocalDate()
                        .atTime(23, 59)
        );
        String extractedText="";


        try {
            if (bookingRequestDto.getResume() != null
                    &&
                    !bookingRequestDto.getResume().isEmpty()) {


                 extractedText =
                        resumeAnalysisService.extractText(
                                bookingRequestDto.getResume()
                        );


                String resumeUrl =
                        s3Service.uploadResume(
                                bookingRequestDto.getResume()
                        );




                bookingEntity.setResumeUrl(resumeUrl);


            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to process resume",
                    e
            );
        }


        credential_repo.save(credentialEntity);

        bookingEntity = bookingRepositroy.save(bookingEntity);
        ResumeTextEntity resumeEntity =null;
        if (bookingEntity.getResumeUrl()!= null ) {
             resumeEntity =
                    ResumeTextEntity.builder()
                            .bookingId(bookingEntity.getId())
                            .resumeUrl(bookingEntity.getResumeUrl())
                            .extractedText(extractedText)
                            .uploadedAt(LocalDateTime.now())
                            .build();

            resumeTextRepo.save(resumeEntity);
            bookingEntity.setExtractedResumeId(resumeEntity.getId());
            bookingRepositroy.save(bookingEntity);


        }
        MailDto mailDto = new MailDto(
                interviewId,
                bookingEntity.getEmail(),
                bookingEntity.getFullName(),
                bookingEntity.getInterview_Domain().toString(),
                bookingEntity.getInterviewDateTime().toString(),
                rawPassword
        );


        publisher.publishEvent(mailDto);

        return maptoDto(bookingEntity, interviewId,resumeEntity);
    }


}
