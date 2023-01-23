package com.myapi.api.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Data
@Entity
@Table(name = "alunos")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String sobrenome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private Integer idade;

    @Column(nullable = false, length = 100)
    private Float peso;

    @Column(nullable = false, length = 100)
    private Float altura;

    @Column(nullable = false, length = 100)
    private Date created_at;

    @Column(nullable = false, length = 100)
    private Date updated_at;

}
