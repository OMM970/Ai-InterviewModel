package org.example.aiinterview.ResumeReadingModel.ModelRepo;

import org.example.aiinterview.ResumeReadingModel.ModelEntity.ResumeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends MongoRepository<ResumeEntity, String> {
}
