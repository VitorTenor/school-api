package com.school.api.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PhotoRegisterDTO {
    private String fileName;

    private String originalName;

    private int aluno_id;

    private MultipartFile file;

    public PhotoRegisterDTO( MultipartFile file, int id) {
        this.file = file;
        this.aluno_id = id;
    }

}
