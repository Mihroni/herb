package com.example.herbhub.repository;

import com.example.herbhub.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    @Query(sort = "{ 'sendAt': -1 }")
    List<Message> findAllByGroupId(String groupId);

    @Query(value = "{ $or: [ { 'senderId': ?0 }, { 'recipientId': ?0 } ], 'groupId': ?1 }", sort = "{ 'sendAt': -1 }")
    List<Message> findAllByUserIdAndGroupId(String userId, String groupId);
}