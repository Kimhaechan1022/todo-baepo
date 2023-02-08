package com.example.todo.userapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserIdDTO {
    private String userId;
    private String userName;
}
