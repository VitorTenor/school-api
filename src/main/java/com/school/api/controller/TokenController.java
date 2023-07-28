package com.school.api.controller;

import com.school.domain.model.UserModel;
import com.school.api.models.AuthDTO;
import com.school.domain.repository.UserRepository;
import com.school.domain.service.TokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    public TokenController(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/")
    @ApiOperation(value = "Return a Bearer Token")
    public ResponseEntity<Object> login(@RequestBody AuthDTO authDTO) {
        Optional<UserModel> optUser = userRepository.findByEmail(authDTO.getEmail());
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        return tokenService.getToken(optUser, authDTO);
    }
}
