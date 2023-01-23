package com.myapi.api.repositories;

import com.myapi.api.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

    boolean existsByEmail(String email);
}

