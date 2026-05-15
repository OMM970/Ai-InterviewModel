package org.example.aiinterview.InterViewBookingService.Repository;

import org.example.aiinterview.InterViewBookingService.Entitiy.BookingEntity;
import org.example.aiinterview.InterViewBookingService.Entitiy.ResumeTextEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeTextRepo extends MongoRepository<ResumeTextEntity, String> {
}
