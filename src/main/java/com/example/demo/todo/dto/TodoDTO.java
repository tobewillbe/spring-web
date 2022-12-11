package com.example.demo.todo.dto;

import com.example.demo.todo.entity.Todo;
import lombok.*;

import java.sql.Date;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    private String id;
    private String title;
    private boolean finish;
    private Date regDate;

    //Todo에서 TodoDTO에서 필요한 필드를 빼오는 생성자
    public TodoDTO(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.finish = todo.isFinish();
        this.regDate = todo.getRegDate();
    }
}
