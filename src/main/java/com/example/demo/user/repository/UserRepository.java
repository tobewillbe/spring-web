package com.example.demo.user.repository;

import com.example.demo.user.entity.UserEntity;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

    UserEntity findUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean register(UserEntity user);
}
