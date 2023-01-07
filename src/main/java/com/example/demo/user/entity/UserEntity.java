package com.example.demo.user.entity;

import com.example.demo.user.dto.UserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter @Getter @ToString
@AllArgsConstructor
public class UserEntity {

    private String id;
    private String username;
    private String email;
    private String password;
    private String profileImg;
    public UserEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public UserEntity(UserRequestDTO dto){
        this();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.username = dto.getUsername();
    }
}