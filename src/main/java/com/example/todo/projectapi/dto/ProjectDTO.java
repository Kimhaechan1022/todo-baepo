package com.example.todo.projectapi.dto;

import com.example.todo.todoapi.dto.TodoDTO;
import com.example.todo.todoapi.entity.TodoEntity;
import com.example.todo.userapi.dto.UserIdDTO;
import com.example.todo.userapi.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ProjectDTO {

    private String projectId;
    private String title;
    private String content;
    private boolean done;


    private LocalDateTime createDate;

    private List<UserIdDTO> members;

    private int memberCount;

    private List<TodoDTO> todos;

}
