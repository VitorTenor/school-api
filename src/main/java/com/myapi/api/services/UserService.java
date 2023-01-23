package com.myapi.api.services;

import com.myapi.api.models.UserModel;
import com.myapi.api.models.dtos.UserDTO;
import com.myapi.api.models.dtos.UserRegisterDTO;
import com.myapi.api.repositories.UserRepository;
import com.myapi.api.util.CripSenha;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService {
    final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserDTO> getAllUsers() {
        List<UserModel> userModel = userRepository.findAll();
        List<UserDTO> userListDTO = listToDto(userModel);
        return userListDTO;
    }

    public UserDTO newUser(UserRegisterDTO userDTO) {
        UserModel userModel = new UserModel();
        userModel.setNome(userDTO.getNome());
        userModel.setEmail(userDTO.getEmail());
        userModel.setPassword_hash(CripSenha.criptografarSenha(userDTO.getPassword()));
        userModel.setCreated_at(new Date());
        userModel.setUpdated_at(new Date());
        userRepository.save(userModel);
        return toDTO(userModel);
    }

    public UserModel updateUser(UserModel userModel) {
        Optional<UserModel> user = userRepository.findById(userModel.getId());
        if(user.isPresent()) {
            user.get().setNome(userModel.getNome());
            user.get().setEmail(userModel.getEmail());
            user.get().setPassword_hash(user.get().getPassword_hash());
            user.get().setUpdated_at(new Date());
            userRepository.save(user.get());
            return user.get();
        }
        return null;
    }

    public Object getUserById(Integer id) {
        UserModel userModel = userRepository.findById(id).get();
        return toDTO(userModel);
    }

    private UserDTO toDTO(UserModel userModel) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setNome(userModel.getNome());
        userDTO.setEmail(userModel.getEmail());
        return userDTO;
    }

    private List<UserDTO> listToDto(List<UserModel> userModel) {
        List<UserDTO> userDTO = new ArrayList<>();
        for (UserModel user : userModel) {
            UserDTO userDTO1 = new UserDTO();
            userDTO1.setId(user.getId());
            userDTO1.setNome(user.getNome());
            userDTO1.setEmail(user.getEmail());
            userDTO.add(userDTO1);
        }
        return userDTO;
    }
}
