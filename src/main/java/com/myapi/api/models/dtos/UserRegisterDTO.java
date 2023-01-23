package com.myapi.api.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {
    private int id;
    private String nome;
    private String email;
    private String password;
}
