package com.todo.controller;

import com.todo.model.ToDoListResp;
import com.todo.model.ToDoReq;
import com.todo.model.ToDoResp;
import com.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/all")
    public ResponseEntity<ToDoListResp> getAllToDos() {
        ToDoListResp response = toDoService.getAllToDos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoResp> getToDo(@PathVariable("id") UUID id) {
        ToDoResp response = toDoService.getToDo(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") UUID id) {
        String response = toDoService.deleteTodo(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ToDoResp> createTodo(@RequestBody ToDoReq toDoReq) {
        ToDoResp response = toDoService.createToDo(toDoReq);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ToDoResp> updateToDo(@RequestBody ToDoReq toDoReq) {
        ToDoResp response = toDoService.updateToDo(toDoReq);
        return ResponseEntity.ok(response);
    }
}
