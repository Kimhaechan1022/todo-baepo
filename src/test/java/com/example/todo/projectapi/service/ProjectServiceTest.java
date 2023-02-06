package com.example.todo.projectapi.service;

import com.example.todo.projectapi.dto.ProjectListDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {


    @Autowired
    ProjectService projectService;


    @Test
    @DisplayName("유저 엔터티로 프로젝트 조회")
    @Transactional
    @Rollback
    void test() {

        ProjectListDTO resDto = projectService.getCurrentUserProjectInfo("402880af86068cce0186068cd7640002");

        assertEquals("project_title",resDto.getList().get(0).getProjectTitle());


    }

}