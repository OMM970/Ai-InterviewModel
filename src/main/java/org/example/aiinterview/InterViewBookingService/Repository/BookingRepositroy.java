package org.example.aiinterview.InterViewBookingService.Repository;

import org.example.aiinterview.InterViewBookingService.Entitiy.BookingEntity;
import org.example.aiinterview.InterViewBookingService.Service.BookingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepositroy extends JpaRepository<BookingEntity, Long> {
}
