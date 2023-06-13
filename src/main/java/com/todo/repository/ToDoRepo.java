package com.todo.repository;

import com.todo.entity.ToDo;
import com.todo.model.ToDoListModel;
import com.todo.model.ToDoResp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, UUID> {

    @Query("select count(td) from ToDo td " +
            "where td.expiredAt is null or td.expiredAt > :date")
    long countAllNotExpired(LocalDateTime date);

    @Query("select new com.todo.model.ToDoListModel(td.id, td.title) from ToDo td " +
            "where td.expiredAt is null or td.expiredAt > :date " +
            "order by td.updatedAt")
    List<ToDoListModel> findAllNotExpired(LocalDateTime date);

    @Query("select new com.todo.model.ToDoResp(td.id, td.title, td.description, td.createdAt, td.updatedAt) from ToDo td " +
            "where td.id = :id and (td.expiredAt is null or td.expiredAt > :date)")
    Optional<ToDoResp> findByIdNotExpired(UUID id);

}
