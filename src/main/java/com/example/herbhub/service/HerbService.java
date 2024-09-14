package com.example.herbhub.service;

import com.example.herbhub.dto.HerbDto;
import com.example.herbhub.dto.ImageDto;
import com.example.herbhub.model.Herb;
import com.example.herbhub.model.Location;
import com.example.herbhub.repository.HerbRepository;
import com.example.herbhub.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HerbService {

    private final HerbRepository herbRepository;
    private final LocationRepository locationRepository;

    public Herb create(HerbDto dto) {
        Herb result = new Herb();
        result.setName(dto.getName());
        result.setDescription(dto.getDescription());
        result.setBenefits(dto.getBenefits());
        List<Location> locations = locationRepository.saveAll(dto.getLocations());
        result.setLocations(locations);
        result.setSeasons(dto.getSeasons());
        result.setImageUrl(dto.getImageUrl());
        return herbRepository.save(result);
    }

    public Herb update(String id, HerbDto dto) {
        Herb result = findById(id);
        result.setName(dto.getName());
        result.setDescription(dto.getDescription());
        result.setBenefits(dto.getBenefits());
        result.setLocations(dto.getLocations());
        result.setSeasons(dto.getSeasons());
        return herbRepository.save(result);
    }

    public void delete(String id) {
        herbRepository.deleteById(id);
    }

    public List<Herb> findAll() {
        return herbRepository.findAll();
    }

    public Herb findById(String id) {
        return herbRepository.findById(id).orElseThrow();
    }

    public Herb findByHerbId(String locationId) {
        return herbRepository.findByLocationsId(locationId);
    }

    public ImageDto saveImage(MultipartFile image) throws IOException {
        // Ensure the upload directory exists
        String imageUploadDirectory = "src/main//resources/static/uploads/";
        Path uploadPath = Paths.get(imageUploadDirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file to the server
        String fileName = image.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, image.getBytes());
        String url = "/uploads/" + fileName;
        ImageDto result = new ImageDto();
        result.setImageUrl(url);
        return result;
    }
}
