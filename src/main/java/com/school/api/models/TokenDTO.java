package com.school.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {
    private String token;
    private TokenUserDTO user;

}
