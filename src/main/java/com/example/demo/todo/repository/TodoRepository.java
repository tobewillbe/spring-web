package com.example.demo.todo.repository;

import com.example.demo.todo.entity.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
// 역할 : 할 일 데이터를 CRUD한다.(생성, 조회, 수정, 삭제)
public interface TodoRepository {
    // 할일 생성기능
    /**
     * 할일 데이터를 저장소에 저장하는 기능
     * @param todo - 할 일 데이터의 집합
     * @return - 저장 성공시 true, 실패시 false 반환
     */
    boolean insert(Todo todo);

    // 할일 목록조회기능
    /**
     *
     * @return - 할일을 리스트로 받아옴
     */
    List<Todo> list_all();

    // 할일 개별조회기능
    /**
     *
     * @param id -할일의 ID를 입력
     * @return - 할 일을 Todo형태로 받아옴
     */
    Todo view_one(String id);

    // 할일 삭제기능
    /**
     *
     * @param id - 삭제하고자 하는 일의 ID를 입력
     * @return -  삭제시 true 실패시 false
     */
    boolean delete(String id);

    // 할일 수정기능
    /**
     *
     * @param todo - 할 일 데이터의 집합
     * @return -  삭제시 true 실패시 false
     */
    boolean modify(Todo todo);
}
