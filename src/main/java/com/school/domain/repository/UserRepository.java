package com.school.domain.repository;

import com.school.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserModel, Integer> {
    boolean existsByEmail(String email);


    void deleteById(Integer id);

    Optional<UserModel> findByEmail(String username);

}
