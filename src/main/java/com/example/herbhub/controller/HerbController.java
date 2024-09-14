package com.example.herbhub.controller;

import com.example.herbhub.dto.HerbDto;
import com.example.herbhub.dto.ImageDto;
import com.example.herbhub.model.Herb;
import com.example.herbhub.service.HerbService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/herbs")
@RequiredArgsConstructor
public class HerbController {

    private final HerbService herbService;

    @PostMapping
    public Herb create(@RequestBody HerbDto dto) {
        return herbService.create(dto);
    }


    @PutMapping("/{id}")
    public Herb update(@RequestBody HerbDto dto, @PathVariable String id) {
        return herbService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        herbService.delete(id);
    }

    @GetMapping("/{id}")
    public Herb findById(@PathVariable String id) {
        return herbService.findById(id);
    }

    @GetMapping("/location")
    public Herb findByHerbId(@RequestParam String locationId) {
        return herbService.findByHerbId(locationId);
    }

    @GetMapping
    public List<Herb> findAll(){
        return herbService.findAll();
    }

    @PostMapping("/upload")
    public ImageDto uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            return herbService.saveImage(image);

        } catch (IOException e) {
            return null;
        }
    }
}
