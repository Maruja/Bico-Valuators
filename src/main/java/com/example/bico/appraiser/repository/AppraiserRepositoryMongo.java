package com.example.bico.appraiser.repository;

import com.example.bico.appraiser.model.mongo.AppraiserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppraiserRepositoryMongo extends MongoRepository<AppraiserDocument, String> {
    // Query in mongodb to find all entities that are deleted or not depending on boolean "deleted"
    List<AppraiserDocument> findByDeleted(boolean deleted);
}
