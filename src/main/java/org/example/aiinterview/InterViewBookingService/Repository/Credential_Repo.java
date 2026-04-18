package org.example.aiinterview.InterViewBookingService.Repository;

import org.example.aiinterview.InterViewBookingService.Entitiy.Credential_Entity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Credential_Repo extends MongoRepository<Credential_Entity, String> {
}
