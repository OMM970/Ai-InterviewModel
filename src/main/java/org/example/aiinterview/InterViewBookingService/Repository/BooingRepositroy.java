package org.example.aiinterview.InterViewBookingService.Repository;

import org.example.aiinterview.InterViewBookingService.Service.BookingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooingRepositroy extends JpaRepository<String, BookingService> {
}
