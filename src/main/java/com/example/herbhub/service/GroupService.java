package com.example.herbhub.service;

import com.example.herbhub.dto.GroupDto;
import com.example.herbhub.model.Group;

import com.example.herbhub.model.Herb;
import com.example.herbhub.model.Location;
import com.example.herbhub.model.Message;
import com.example.herbhub.model.User;
import com.example.herbhub.repository.GroupRepository;
import com.example.herbhub.repository.HerbRepository;
import com.example.herbhub.repository.LocationRepository;
import com.example.herbhub.repository.MessageRepository;
import com.example.herbhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final AuthenticationService authenticationService;
    private final LocationRepository locationRepository;
    private final HerbRepository herbRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public Group create(GroupDto dto) {
        Group group = new Group();
        group.setDescription(dto.getDescription());
        group.setParticipants(new ArrayList<>());
        group.getParticipants().add(authenticationService.getLoggedUserId());
        Location location = locationRepository.findById(dto.getLocationId()).orElseThrow();
        Herb herb = herbRepository.findByLocationsId(location.getId());
        group.setHerbId(herb.getId());
        group.setLocationId(dto.getLocationId());
        group.setGatheringTime(dto.getGatheringTime());
        return groupRepository.save(group);
    }


    public Group update(String id, GroupDto dto) {
        Group group = findById(id);
        group.setGatheringTime(dto.getGatheringTime());
        group.setDescription(dto.getDescription());
        return groupRepository.save(group);
    }

    public Group findById(String id) {
        return groupRepository.findById(id).orElseThrow();
    }

    public void delete(String id) {
        this.groupRepository.deleteById(id);
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public List<Group> findAllByLocationId(String locationId) {
        List<Group> groups = groupRepository.findByLocationId(locationId);
        groups.forEach(group -> {
            List<String> participants = group.getParticipants();
            for (int i = 0; i < participants.size(); i++) {
                String participantId = participants.get(i);
                Optional<User> user = userRepository.findById(participantId);
                if (user.isPresent()) {
                    participants.set(i, user.get().returnName());
                }
            }
        });
        return groups;
    }

    public void activity(String id) {
        Group group = findById(id);
        String loggedUsrId = authenticationService.getLoggedUserId();
        if (group.getParticipants().contains(loggedUsrId)){
            group.getParticipants().remove(loggedUsrId);
        } else {
            group.getParticipants().add(loggedUsrId);
        }
        this.groupRepository.save(group);
    }

    public List<Message> viewChat(String id) {
        List<Message> messages =  messageRepository.findAllByGroupId(id);
        messages.forEach(message -> {
            User sender = userRepository.findById(message.getSenderId()).orElseThrow();
            message.setSenderId(sender.returnName());
        });
        return messages;
    }
}
