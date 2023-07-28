package com.school.domain.repository;

import com.school.domain.model.PhotoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface PhotoRepository extends JpaRepository<PhotoModel, Integer> {
    @Query(value = "SELECT * FROM fotos WHERE aluno_id = :id order by id DESC ", nativeQuery = true)
    ArrayList<PhotoModel> findAllByStudentId(int id);

    @Query(value = "SELECT * FROM fotos WHERE filename = :fileName", nativeQuery = true)
    PhotoModel findByFileName(String fileName);

}
