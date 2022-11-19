package com.example.demo.todo.repository;

import com.example.demo.todo.entity.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoRepositoryMemoryImplTest {

    @Autowired TodoRepository repository;

    //단언
    @Test
    @DisplayName("저장소에서 목록을 조회했을 때 할 일의 개수가 3개여야 한다.")
    void listAllTest(){
        // given : 테스트시 필요한 데이터

        // when : 테스트에 실제상황
        List<Todo> todoList = repository.list_all();
        // then : 테스트 결과 단언
        assertEquals(3, todoList.size());
        assertNotNull(todoList);
    }

    @Test
    @DisplayName("아이디가 2번인 할 일 데이터를 조회했을때 그 작성")
    void viewOneTest(){
        // given
        long id = 2L;
        // when
        Todo todo = repository.view_one(id);
        // then
        assertEquals("박영희", todo.getUserId());
        assertFalse(todo.isFinish());
    }

    @Test
    @DisplayName("1번 할일을 삭제한후 다시 조회했을때 null이 나와야한다.")
    void deleteTest(){
    //given
    long id = 1L;
    //when
    boolean flag = repository.delete(id);
    Todo todo = repository.view_one(id);
    //then
    assertTrue(flag);
    assertNull(todo);
    assertEquals(2, repository.list_all().size());
    }

    @Test
    @DisplayName("")
    void insertTest(){
    //given
    Todo newTodo = new Todo(4L, "박영희", "청소기돌리기", false);
    //when
    boolean flag = repository.insert(newTodo);
    Todo todo = repository.view_one(newTodo.getId());
    //then
    assertTrue(flag);
    assertNotNull(todo);
    assertEquals(4,repository.list_all().size());
    }

}