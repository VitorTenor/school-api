package com.myapi.api.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenUserDTO {
    private String nome;
    private int id;
    private String email;
}
