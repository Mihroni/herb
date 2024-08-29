package com.example.herbhub.model;

import com.example.herbhub.util.Season;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "herb")
public class Herb {
    @Id
    private String id;
    private String name;
    private String description;
    private List<Season> seasons;
    private String benefits;
    private List<Location> locations;
}