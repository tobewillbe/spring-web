package com.example.demo.todo.dto;

import com.example.demo.todo.entity.Todo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListAllDTO {
    private int count;
    private List<TodoDTO> todos; //userId가 빠진 할 일 목록

    public ListAllDTO(List<Todo> todoList){
        this.count = todoList.size();
        this.convertDTOList(todoList);
    }

    //List<todo>를 전달받으면 List<TodoDTO>로 변환하는 메서드

    public void convertDTOList (List<Todo> todoList){
        List<TodoDTO> dtos = new ArrayList<>();
        for (Todo todo : todoList) {
            dtos.add(new TodoDTO(todo));
        }
        this.todos = dtos;
    }
}

