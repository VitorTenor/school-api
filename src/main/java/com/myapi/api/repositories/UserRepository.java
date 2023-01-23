package com.myapi.api.repositories;

import com.myapi.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserModel, Integer> {
    boolean existsByEmail(String email);


    void deleteById(Integer id);

    Optional<UserModel> findByEmail(String username);

}
