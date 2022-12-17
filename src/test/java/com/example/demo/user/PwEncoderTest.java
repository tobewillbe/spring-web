package com.example.demo.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PwEncoderTest {

    @Autowired
    PasswordEncoder encoder;

    @Test
    @DisplayName("평문 패스워드가 암호화 되어야한다!")
    void pwEncodingTest() {
        // given
        String rawPw = "abc1234!";
        // when
        String encodedPw = encoder.encode(rawPw);
        // then
        System.out.println("encodedPw = " + encodedPw);
    }

    @Test
    @DisplayName("평문패스워드와 암호화된 패스워드를 비교해야한다.")
    void encodingPwLoginTest(){
        //given
        String rawPw="apple123";
        String encodePw = encoder.encode(rawPw);
        //when
//        Boolean flag = rawPw.equals(encodePw);
        boolean flag = encoder.matches(rawPw,encodePw);
        //then
        System.out.println(flag);
    }
}
