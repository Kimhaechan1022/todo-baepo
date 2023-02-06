package com.example.todo.projectapi.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * “projectId” : “string”,
 *
 * “projectTitle” : “string”,
 *
 * “done” : “boolean”,
 *
 * “createDate” : “LocalDateTime”,
 *
 */

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ProjectDetailsDTO {
    private String projectId;
    private String projectTitle;
    private boolean done;

    private LocalDateTime createDate;

    private List<String> members;

    private int memberCount;

}
