package com.myapi.api.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class StudentDTO {
    private int id;
    private String nome;

    private String sobrenome;

    private String email;

    private Integer idade;

    private Float peso;

    private Float altura;

    private ArrayList<PhotoDTO> Fotos;
}
