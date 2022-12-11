package com.example.demo.ibatis;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface TestRepository {
    // 데이터 저장기능
    boolean save(TestEntity entity);

    // 데이터 수정기능
    boolean modify(TestEntity entity);

    // 데이터 삭제기능
    boolean remove(String id);

    // 데이터 전체 조회 기능
    List<TestEntity> findAll();
}
