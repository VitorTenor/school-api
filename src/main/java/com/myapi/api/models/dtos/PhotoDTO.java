package com.myapi.api.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDTO {

    private String url = "http://localhost:8080/fotos/" ;
    private String fileName;
    private int id;
}
