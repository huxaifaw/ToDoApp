package com.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "TODO")
public class ToDo {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @NonNull
    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @NonNull
    @Column(name = "CREATE_TS")
    private LocalDateTime createdAt;

    @NonNull
    @Column(name = "UPD_TS")
    private LocalDateTime updatedAt;

    @Column(name = "EXP_TS")
    private LocalDateTime expiredAt;

}
