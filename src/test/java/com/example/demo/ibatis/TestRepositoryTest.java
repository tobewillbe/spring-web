package com.example.demo.ibatis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TestRepositoryTest {


    @Autowired TestRepository repository;

    @Test
    @DisplayName("유저이름과 나이가 TBL_TEST에 저장되어야한다.")
    void saveTest() {
        //given : 테스트상황에 주어질 데이터
        TestEntity entity = new TestEntity();
        entity.setUsername("박희숙");
        entity.setAge(40);

        //when: 실제 테스트 실행
        boolean flag = repository.save(entity);

        //then: 테스트 결과 단언
        assertTrue(flag);
        assertNotNull(entity.getId());
    }

    @Test
    @DisplayName("유저이름과 나이를 수정해야 한다.")
    void modifyTest() {
        //given
        TestEntity entity = new TestEntity();
        String id="1597d9b1-3ca2-45a6-8d9a-ef479559806f";

        entity.setUsername("최수정");
        entity.setAge(25);
        entity.setId(id);

        //when
        boolean flag = repository.modify(entity);

        //then
        assertTrue(flag);
    }


    @Test
    @DisplayName("유저가 삭제되어야한다.")
    @Transactional
    @Rollback
    void deleteTest(){
        //given
        String id="1597d9b1-3ca2-45a6-8d9a-ef479559806f";

        //when
        boolean flag = repository.remove(id);

        //then
        assertTrue(flag);
    }

}