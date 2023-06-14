package com.todo.service;

import com.todo.entity.ToDo;
import com.todo.exception.ResourceNotFoundException;
import com.todo.model.ToDoListModel;
import com.todo.model.ToDoListResp;
import com.todo.model.ToDoReq;
import com.todo.model.ToDoResp;
import com.todo.repository.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.todo.constant.ToDoConstants.ENTITY_NOT_FOUND;
import static com.todo.constant.ToDoConstants.ENTITY_REMOVED;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepo toDoRepo;

    public ToDoListResp getAllToDos() {
        LocalDateTime date = LocalDateTime.now();
        long count = toDoRepo.countAllNotExpired(date);
        List<ToDoListModel> toDoListModelList = new ArrayList<>();
        if (count > 0L) {
            toDoListModelList = toDoRepo.findAllNotExpired(date);
        }
        return new ToDoListResp(count, toDoListModelList);
    }

    public ToDoResp getToDo(UUID id) {
        return toDoRepo.findByIdNotExpired(id).orElseThrow(
                () -> new ResourceNotFoundException(ENTITY_NOT_FOUND));
    }

    public String deleteTodo(UUID id) {
        ToDo todo = toDoRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(ENTITY_NOT_FOUND));

        LocalDateTime date = LocalDateTime.now();
        todo.setUpdatedAt(date);
        todo.setExpiredAt(date);
        toDoRepo.save(todo);
        return ENTITY_REMOVED;
    }

    public ToDoResp createToDo(ToDoReq toDoReq) {
        ToDo todo = new ToDo();
        return createUpdateTodo(todo, toDoReq);
    }

    public ToDoResp updateToDo(ToDoReq toDoReq) {
        ToDo todo = toDoRepo.findById(toDoReq.getId()).orElseThrow(
                () -> new ResourceNotFoundException(ENTITY_NOT_FOUND));
        return createUpdateTodo(todo, toDoReq);
    }

    private ToDoResp createUpdateTodo(ToDo todo, ToDoReq toDoReq) {
        if (!"".equals(toDoReq.getTitle().trim())) {
            todo.setTitle(toDoReq.getTitle());
        }
        if (!"".equals(toDoReq.getDescription().trim())) {
            todo.setDescription(toDoReq.getDescription());
        }

        LocalDateTime date = LocalDateTime.now();
        if (todo.getId() == null) {
            todo.setCreatedAt(date);
        }
        todo.setUpdatedAt(date);

        todo = toDoRepo.save(todo);

        return new ToDoResp(todo.getId(), todo.getTitle(), todo.getDescription(), todo.getCreatedAt(), todo.getUpdatedAt());
    }
}
