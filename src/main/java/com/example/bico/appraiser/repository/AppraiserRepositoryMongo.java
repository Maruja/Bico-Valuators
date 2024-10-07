package com.example.bico.appraiser.repository;

import com.example.bico.appraiser.model.mongo.AppraiserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppraiserRepositoryMongo extends MongoRepository<AppraiserDocument, String> {
}
