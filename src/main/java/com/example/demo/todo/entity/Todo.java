package com.example.demo.todo.entity;

import lombok.*;

import java.sql.Date;
import java.util.UUID;


@Setter @Getter @ToString
@AllArgsConstructor
//역할 : 하나의 할일 데이터의 집합 객체
public class Todo {

    private String id; // 할일들을 식별하는 번호
    private String userId; // 할일을 등록한 회원의 식별자
    private String title; // 할일내용
    private boolean finish; // 할일완료여부
    private Date regDate; // 할일완료여부
    private static long seq;
    public Todo() {
        this.id = UUID.randomUUID().toString();
    }

    public Todo(String title)
    {
        this();
        this.title = title;
        this.userId = "noname";
    }
}
