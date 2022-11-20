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

    public ListAllDTO listAllServ(){
        return new ListAllDTO(repository.list_all());
    }

    public ListAllDTO createServ(final Todo newTodo) {

        if (newTodo == null){
            log.warn("new Todo cannot be null!");
            throw new RuntimeException("newTodo Cannot be null!");
        }

        boolean flag = repository.insert(newTodo);
        if(flag) log.info("새로운 할일 id:{}이 저장되었습니다.",newTodo.getId());
        return flag ? listAllServ() : null;
    }

    public TodoDTO view_one(final long id) {

        if (id==0){
            log.warn("id cannot be null!");
            throw new RuntimeException("id cannot be null!");
        }
        return new TodoDTO(repository.view_one(id));
    }

    public ListAllDTO delete(long id) {
        boolean flag = repository.delete(id);

        if(!flag){
            log.warn("delete fail!! not found id[{}]", id);
            throw new RuntimeException("delete fail!");
        }
        else log.info("새로운 할일 id:{}가 삭제되었습니다.",id);
        return listAllServ();
    }
}
