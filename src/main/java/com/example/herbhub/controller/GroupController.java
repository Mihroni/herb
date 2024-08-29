package com.example.herbhub.controller;

import com.example.herbhub.dto.GroupDto;
import com.example.herbhub.model.Group;
import com.example.herbhub.model.Message;
import com.example.herbhub.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public Group create(@RequestBody GroupDto dto) {
        return groupService.create(dto);
    }


    @PutMapping("/{id}")
    public Group update(@RequestBody GroupDto dto, @PathVariable String id) {
        return groupService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        groupService.delete(id);
    }

    @GetMapping("/{id}")
    public Group findById(@PathVariable String id) {
        return groupService.findById(id);
    }

    @GetMapping
    public List<Group> findAll(){
        return groupService.findAll();
    }

    @GetMapping("/location")
    public List<Group> findAll(@RequestParam String locationId){
        return groupService.findAllByLocationId(locationId);
    }

    @GetMapping("/activity/{id}")
    public void activity(@PathVariable String id){
        groupService.activity(id);
    }

    @GetMapping("/chat/{id}")
    public List<Message> viewChat(@PathVariable String id){
       return groupService.viewChat(id);
    }
}
