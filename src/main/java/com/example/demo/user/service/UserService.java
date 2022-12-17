package com.example.demo.user.service;

import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    //회원 가입 기능
    public UserEntity createServ(final UserEntity userEntity){
        if(userEntity == null || userEntity.getEmail() == null){
            throw new RuntimeException("Invalid args");
        }
        // 패스워드 인코딩
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        boolean flag = userRepository.register(userEntity);

        return flag ? getByCredential(userEntity.getEmail()) :
                null;
    }

    public UserEntity getByCredential(String email) {
        return userRepository.findUserByEmail(email);
    }

    //로그인 검증 메서드
    public UserEntity validateLogin(final String email, final String password){
        UserEntity user = getByCredential(email);

        if(user == null) throw new RuntimeException("가입한회원이 아닙니다.");

        if(!encoder.matches(password, user.getPassword())){
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        return user;
    }

    public boolean isDuplicate(String email){
        return userRepository.existsByEmail(email);
    }
}
