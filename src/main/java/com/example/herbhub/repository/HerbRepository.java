package com.example.herbhub.repository;

import com.example.herbhub.model.Herb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HerbRepository extends MongoRepository<Herb, String> {
        Herb findByLocationsId(String locationId);
}
