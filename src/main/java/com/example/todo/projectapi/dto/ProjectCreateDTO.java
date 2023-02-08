package com.example.todo.projectapi.dto;

import com.example.todo.todoapi.dto.TodoDTO;
import com.example.todo.userapi.dto.UserIdDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ProjectCreateDTO {


    private String projectId;
    private String title;
    private String content;
    private boolean done;

    private String userId;
    private LocalDateTime createDate;

    private List<UserIdDTO> members;

    private int memberCount;

    private List<TodoDTO> todos;
}
