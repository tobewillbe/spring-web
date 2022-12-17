package com.example.demo.user.api;

import com.example.demo.security.TokenProvider;
import com.example.demo.user.dto.UserRequestDTO;
import com.example.demo.user.dto.UserResponseDTO;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class UserApiController {

    private final UserService userService;
    private final TokenProvider provider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO reqDTO){

        try {
            //userReqDto를 서비스에 전송
            //dto를 Entity로 변환
            UserEntity userEntity = new UserEntity(reqDTO);
            log.info("/auth/signup POST!! -{}", userEntity);
            UserEntity user = userService.createServ(userEntity);

            return ResponseEntity.ok().body(new UserResponseDTO(userEntity));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserRequestDTO dto){
        log.info("/auth/signin POST!! - login Info : {} ", dto);

        try {
            UserEntity user = userService.validateLogin(dto.getEmail(), dto.getPassword());
            //토큰생성
            final String token = provider.create(user);
            UserResponseDTO responseDTO = new UserResponseDTO(user);
            responseDTO.setToken(token);

            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
