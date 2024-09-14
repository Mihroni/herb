package com.example.herbhub.dto;

import com.example.herbhub.model.Location;
import com.example.herbhub.util.Season;
import lombok.Data;

import java.util.List;

@Data
public class HerbDto {
    private String name;
    private String description;
    private List<Season> seasons;
    private String benefits;
    private List<Location> locations;
    private String imageUrl;
}
