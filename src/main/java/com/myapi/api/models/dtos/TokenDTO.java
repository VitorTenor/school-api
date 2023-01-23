package com.myapi.api.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {
    private String token;
    private TokenUserDTO user;

}
