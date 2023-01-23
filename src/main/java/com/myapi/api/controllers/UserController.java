package com.myapi.api.controllers;

import com.myapi.api.models.UserModel;
import com.myapi.api.models.dtos.UserDTO;
import com.myapi.api.models.dtos.UserRegisterDTO;
import com.myapi.api.repositories.UserRepository;
import com.myapi.api.services.UserService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;


    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    @ApiOperation(value = "Get All Users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping
    @ApiOperation(value = "Register User")
    public ResponseEntity<Object> newUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        if(userRepository.existsByEmail(userRegisterDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email já cadastrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.newUser(userRegisterDTO));
    }

    @PutMapping
    @ApiOperation(value = "Update User")
    public ResponseEntity<Object> updateUser(@RequestBody UserModel userModel) {
        if(userRepository.existsById(userModel.getId()) == true){
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userModel));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get User By Id")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") Integer id) {
        if(userRepository.existsById(id) == true){
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete User By Id")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Integer id) {
        if(userRepository.existsById(id) == true){
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
    }
}
