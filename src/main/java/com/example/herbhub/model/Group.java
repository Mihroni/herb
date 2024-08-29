package com.example.herbhub.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "group")
public class Group {
    @Id
    private String id;
    private List<String> participants;
    private String description;
    private LocalDateTime gatheringTime;
    private String herbId;
    private String locationId;
}
