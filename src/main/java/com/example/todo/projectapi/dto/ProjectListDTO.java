package com.example.todo.projectapi.dto;

import lombok.*;

import java.util.List;



@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ProjectListDTO {
    private List<ProjectDetailsDTO> list;
}
