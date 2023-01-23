package com.myapi.api.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Data
@Entity
@Table(name = "fotos")
public class PhotoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String originalname;

    @Column(nullable = false, length = 100)
    private String filename;

    @Column(nullable = false, length = 100)
    private int aluno_id;

    @Column(nullable = false, length = 100)
    private Date created_at;

    @Column(nullable = false, length = 100)
    private Date updated_at;
}
