package com.example.herbhub.repository;

import com.example.herbhub.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token, String> {

    List<Token> findAllByUserIdAndExpiredFalseAndRevokedFalse(String userId);

    Optional<Token> findByToken(String token);
}
