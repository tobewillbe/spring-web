package com.example.demo.todo.repository;

import com.example.demo.todo.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 역할: 할 일 데이터를 메모리에 CRUD하는 역할
@Repository
public class TodoRepositoryMemoryImpl implements TodoRepository {

    // 메모리 저장소
    /**
     * key : 할 일의 식별 id 값
     * value : 할 일 집합객체
     */
    private static final Map<Long,Todo> todoMap = new HashMap<>();

    static {
        todoMap.put(1L,new Todo("저녁밥만들기"));
        todoMap.put(2L,new Todo("산책가기"));
        todoMap.put(3L,new Todo("노래연습하기"));
        todoMap.put(4L,new Todo("리엑트 컴포넌트 만들기"));
    }

    @Override
    public boolean insert(Todo todo) {
        if(todo == null) return false;

        todoMap.put(todo.getId(), todo);
        return true;
    }

    @Override
    public List<Todo> list_all() {

        List<Todo> todoList = new ArrayList<>();
        for (Long id : todoMap.keySet()) {
            Todo todo = todoMap.get(id);
            todoList.add(todo);
        }
        return todoList;
    }

    @Override
    public Todo view_one(Long id) {
        return todoMap.get(id);
    }

    @Override
    public boolean delete(Long id) {
        return todoMap.remove(id) != null;
    }
}
