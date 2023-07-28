package com.school.domain.repository;

import com.school.domain.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

    boolean existsByEmail(String email);
}

