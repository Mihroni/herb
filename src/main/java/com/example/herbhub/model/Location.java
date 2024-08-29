package com.example.herbhub.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "location")
public class Location {
    @Id
    private String id;
    private String x;
    private String y;
}