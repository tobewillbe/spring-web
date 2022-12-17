package com.example.demo.todo.service;

import com.example.demo.todo.dto.ListAllDTO;
import com.example.demo.todo.dto.TodoDTO;
import com.example.demo.todo.entity.Todo;
import com.example.demo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


// 역할 :  컨트롤러와 저장소 사이의 잡일 처리
@Service
@Slf4j
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    /*
        -할 일 목록 조회 중간처리
        1. 컨트롤러에게 userId를 뺀 할일 리스트를 전달한다.
        2. 할 일 목록의 카운트를 세서 따로 추가해서 전달한다.
     */

    public ListAllDTO listAllServ(String userId){
        return new ListAllDTO(repository.list_all(userId));
    }

    public ListAllDTO createServ(final Todo newTodo, String userId) {

        if (newTodo == null){
            log.warn("new Todo cannot be null!");
            throw new RuntimeException("newTodo Cannot be null!");
        }

        boolean flag = repository.insert(newTodo);
        if(flag) log.info("새로운 할일 id:{}이 저장되었습니다.",newTodo.getId());
        return flag ? listAllServ(userId) : null;
    }

    public TodoDTO view_one(final String id) {

        if (id==null){
            log.warn("id cannot be null!");
            throw new RuntimeException("id cannot be null!");
        }
        return new TodoDTO(repository.view_one(id));
    }

    public ListAllDTO delete(String id, String userId) {
        boolean flag = repository.delete(id);
        //삭제 실패할경우
        if(!flag){
            log.warn("delete fail!! not found id[{}]", id);
            throw new RuntimeException("delete fail!");
        }
        return listAllServ(userId);
    }
    public ListAllDTO update(Todo toDo) {

        boolean flag = repository.modify(toDo);

        return flag ? listAllServ(toDo.getUserId()) : new ListAllDTO();
    }
}
