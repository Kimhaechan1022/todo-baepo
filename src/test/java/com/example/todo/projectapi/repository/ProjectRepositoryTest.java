package com.example.todo.projectapi.repository;

import com.example.todo.logapi.entity.LogEntity;
import com.example.todo.projectapi.entity.ProjectEntity;
import com.example.todo.todoapi.entity.TodoEntity;
import com.example.todo.todoapi.repository.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TodoRepository todoRepository;

    @Test
    @DisplayName("프로젝트 아이디로 하위의 모든 로그 조회")
    @Transactional
    void selectAllLogs(){
//
//        ProjectEntity dept
//                = projectRepository.findById("id1")
//                .orElseThrow();
//
//        List<TodoEntity> todos = dept.getTodos();
//
//
//        todos.forEach(System.out::println);



        TodoEntity dept
                = todoRepository.findById("todoid1")
                .orElseThrow();

        List<LogEntity> logs = dept.getLogs();


        logs.forEach(System.out::println);


    }

}