package com.example.todo.projectapi.service;

import com.example.todo.projectapi.dto.ProjectCreateDTO;
import com.example.todo.projectapi.dto.ProjectDTO;
import com.example.todo.projectapi.dto.ProjectListDTO;
import com.example.todo.projectapi.entity.ProjectEntity;
import com.example.todo.projectapi.repository.ProjectRepository;
import com.example.todo.userapi.dto.UserIdDTO;
import com.example.todo.userapi.entity.UserEntity;
import com.example.todo.userapi.entity.UserProjectEntity;
import com.example.todo.userapi.repository.UserProjectRepository;
import com.example.todo.userapi.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {


    @Autowired
    ProjectService projectService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProjectRepository userProjectRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    @DisplayName("유저 엔터티로 프로젝트 조회")
    @Transactional
    @Rollback
    void test() {

        ProjectListDTO resDto = projectService.getCurrentUserProjectInfo("402880af86068cce0186068cd7640002");

        assertEquals("project_title",resDto.getList().get(0).getProjectTitle());


    }


    @Test
    @DisplayName("프로젝트 아이디로 모든 세부사항 조회, DTO 생성, 반환")
    @Transactional
    @Rollback
    void test2() {

        List<UserIdDTO> members = new ArrayList<>();
        UserIdDTO user1 = UserIdDTO.builder()
                .userId("402880af85f0876c0185f095d1280002")
                .userName("userName1")
                .build();
        UserIdDTO user2 = UserIdDTO.builder()
                .userId("402880af86068cce0186068cd7640002")
                .userName("userName2")
                .build();
        members.add(user1);
        members.add(user2);



        ProjectCreateDTO projectCreateDTO = ProjectCreateDTO.builder()
                .title("t1")
                .userId("402880af85f0876c0185f095d1280002")
                .content("c1")
                .members(members)
                .build();

        ProjectEntity newProject = ProjectEntity.builder()
                .title(projectCreateDTO.getTitle())
                .userId(projectCreateDTO.getUserId())
                .contents(projectCreateDTO.getContent())
                .done(false)
                .createDate(LocalDateTime.now())
                .build();

        ProjectListDTO project = projectService.createProject(projectCreateDTO);


        int a=1;


    }

}