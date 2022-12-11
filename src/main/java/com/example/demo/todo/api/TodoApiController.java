package com.example.demo.todo.api;

import com.example.demo.todo.dto.ListAllDTO;
import com.example.demo.todo.entity.Todo;
import com.example.demo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@CrossOrigin // 다른 서버의 요청 허용
public class TodoApiController {

    private final TodoService service;

    // 할 일 목록 전체요청
    @GetMapping
    public ResponseEntity<?> todos(){
        log.info("/api/todos GET request!");
        return ResponseEntity.ok().body(service.listAllServ());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Todo newTodo){
        newTodo.setUserId("noname");
        log.info("/api/todos POST request!-{}", newTodo);

        try{
            ListAllDTO dto = service.createServ(newTodo);

            if(dto == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(dto);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 할 일 개별 조회 요청
    // URI: /api/todos/3 :GET => 3번 할 일 만 조회해서 클라이언트에게 리턴
    @GetMapping("/{id}")
    public ResponseEntity<?> gets(@PathVariable String id ){
        log.info("/api/todos/{} GET request!", id);

        if (id == null) return ResponseEntity.badRequest().build();
        try {
            return ResponseEntity.ok().body(service.view_one(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // 할 일 삭제 요청
    // URI : /api/todos/3 :DELETE => 3번 할 일만 삭제된 이후 갱신된 할 일 목록 리턴
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        log.info("/api/todos/{} DELETE request!", id);
        try{
            return ResponseEntity.ok().body(service.delete(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // 할일 수정 요청
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Todo toDo) {

        log.info("/api/todos PUT request! - {}", toDo);

        try {
            ListAllDTO dtos = service.update(toDo);
            return ResponseEntity.ok().body(dtos);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
