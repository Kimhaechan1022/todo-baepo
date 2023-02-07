package com.example.todo.logapi.dto;

import com.example.todo.todoapi.entity.TodoEntity;
import com.example.todo.userapi.entity.UserEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class LogDTO {


    private String logId;

    private String title;

    private String contents;

    private boolean done;

    private LocalDateTime createDate; // 등록 시간


    private String userName;


}
