package com.example.herbhub.service;

import com.example.herbhub.dto.AuthenticationResponseDto;
import com.example.herbhub.dto.LoginDto;
import com.example.herbhub.dto.UserDto;
import com.example.herbhub.model.Token;
import com.example.herbhub.model.User;
import com.example.herbhub.repository.TokenRepository;
import com.example.herbhub.repository.UserRepository;
import com.example.herbhub.util.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole(Roles.USER.toString());
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);
    }

    public AuthenticationResponseDto login(LoginDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        AuthenticationResponseDto result = new AuthenticationResponseDto();
        result.setRole(Roles.valueOf(user.getRole()));
        result.setToken(jwtToken);
        result.setUsername(user.returnName());
        result.setUserId(user.getId());
        return result;
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token();
        token.setUserId(user.getId());
        token.setExpired(false);
        token.setRevoked(false);
        token.setToken(jwtToken);
        token.setTokenType("BEARER");
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllByUserIdAndExpiredFalseAndRevokedFalse(user.getId());
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(
                token -> {
                    token.setExpired(true);
                    token.setRevoked(true);
                });
        tokenRepository.saveAll(validUserTokens);
    }

}