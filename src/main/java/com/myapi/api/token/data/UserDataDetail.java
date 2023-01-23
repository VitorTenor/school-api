package com.myapi.api.token.data;

import com.myapi.api.models.UserModel;
import com.myapi.api.models.dtos.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UserDataDetail implements UserDetails {

    private final Optional<UserModel> usuario;

    public UserDataDetail(Optional<UserModel> usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuario.orElse(new UserModel()).getPassword_hash();
    }

    @Override
    public String getUsername() {
        return usuario.orElse(new UserModel()).getEmail();
    }

    public UserDTO getUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(usuario.orElse(new UserModel()).getId());
        userDTO.setNome(usuario.orElse(new UserModel()).getNome());
        userDTO.setEmail(usuario.orElse(new UserModel()).getEmail());
        return userDTO;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
