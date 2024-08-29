package com.example.herbhub.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GroupDto {
    private String description;
    private LocalDateTime gatheringTime;
    private String locationId;
}
