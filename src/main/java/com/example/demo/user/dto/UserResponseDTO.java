package com.example.demo.user.dto;

import com.example.demo.user.entity.UserEntity;
import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String email;
    private String username;
    private String token;

    public UserResponseDTO(UserEntity entity){
        this.email = entity.getEmail();
        this.username = entity.getUsername();
    }
}
