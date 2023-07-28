package com.school.domain.service;

import com.school.domain.model.UserModel;
import com.school.api.models.AuthDTO;
import com.school.api.models.TokenDTO;
import com.school.api.models.TokenUserDTO;
import com.school.core.util.CripSenha;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TokenService {
    public static ResponseEntity<Boolean> validLogin(Optional<UserModel> optUser, AuthDTO authDTO) {
        UserModel user = optUser.get();
        boolean valid = CripSenha.validarSenha(authDTO.getPassword(), user.getPassword_hash());
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }

    public ResponseEntity<Object> getToken(Optional<UserModel> optUser, AuthDTO authDTO) {
        Boolean isValid = validLogin(optUser, authDTO).getBody();
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/getToken";
        String token = restTemplate.postForObject(url, authDTO, String.class);
        return ResponseEntity.status(HttpStatus.OK).body(makeTokenDTO(token, optUser));
    }

    private TokenDTO makeTokenDTO(String token, Optional<UserModel> optUser) {
        TokenUserDTO tokenUserDTO = new TokenUserDTO();
        tokenUserDTO.setId(optUser.get().getId());
        tokenUserDTO.setEmail(optUser.get().getEmail());
        tokenUserDTO.setNome(optUser.get().getNome());
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        tokenDTO.setUser(tokenUserDTO);
        return tokenDTO;
    }
}
