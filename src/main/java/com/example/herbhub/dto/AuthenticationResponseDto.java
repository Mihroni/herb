package com.example.herbhub.dto;

import com.example.herbhub.util.Roles;
import lombok.Data;

@Data
public class AuthenticationResponseDto {
    private String token;
    private String username;
    private Roles role;
    private String userId;
}
