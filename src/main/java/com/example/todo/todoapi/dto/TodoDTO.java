package com.example.todo.todoapi.dto;

import com.example.todo.logapi.dto.LogDTO;
import com.example.todo.logapi.entity.LogEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class TodoDTO {


    private String todoId;

    private String title;

    private boolean done;

    private LocalDateTime createDate;
    private String userName;

    private List<LogDTO> logs;
}
