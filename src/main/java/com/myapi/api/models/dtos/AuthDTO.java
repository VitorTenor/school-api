package com.myapi.api.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class AuthDTO {
    private String email;
    private String password;

}
