package com.example.herbhub.service;

import com.example.herbhub.dto.HerbDto;
import com.example.herbhub.model.Herb;
import com.example.herbhub.model.Location;
import com.example.herbhub.repository.HerbRepository;
import com.example.herbhub.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
