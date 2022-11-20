package com.example.demo.todo.dto;

import com.example.demo.todo.entity.Todo;
import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    private long id;
    private String title;
    private boolean finish;

    //Todo에서 TodoDTO에서 필요한 필드를 빼오는 생성자
    public TodoDTO(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.finish = todo.isFinish();
    }
}
