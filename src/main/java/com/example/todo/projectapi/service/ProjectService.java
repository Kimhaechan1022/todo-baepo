package com.example.todo.projectapi.service;


import com.example.todo.logapi.dto.LogDTO;
import com.example.todo.logapi.entity.LogEntity;
import com.example.todo.projectapi.dto.ProjectDTO;
import com.example.todo.projectapi.dto.ProjectDetailsDTO;
import com.example.todo.projectapi.dto.ProjectListDTO;
import com.example.todo.projectapi.entity.ProjectEntity;
import com.example.todo.projectapi.exceptions.ProjectNotFoundException;
import com.example.todo.projectapi.repository.ProjectRepository;
import com.example.todo.todoapi.dto.TodoDTO;
import com.example.todo.todoapi.entity.TodoEntity;
import com.example.todo.userapi.entity.UserEntity;
import com.example.todo.userapi.entity.UserProjectEntity;
import com.example.todo.userapi.repository.UserProjectRepository;
import com.example.todo.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {

    private final UserProjectRepository userProjectRepository;
    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    // 유저 아이디로 프로젝트 리스트 조회
    // 조회된 프로젝트 아이디로 참여 유저 조회
    public List<UserProjectEntity> getCurrentUserProjectList(String userId){
        UserEntity currentUserEntity = userRepository.findById(userId).orElseThrow();
        List<UserProjectEntity> currentUserProjectList = userProjectRepository.findByUser(currentUserEntity);
        if(currentUserProjectList.isEmpty()){
            throw new ProjectNotFoundException("해당 유저로 조회되는 프로젝트가 없습니다.");
        }
        return currentUserProjectList;

    }
    public  List<ProjectEntity> getUserProjectList(List<UserProjectEntity> userProjectEntityList){
        List<ProjectEntity> userProjectList = new ArrayList<>();
        for(UserProjectEntity e : userProjectEntityList){
            userProjectList.add(e.getProject());
        }
        return userProjectList;
    }

    public ProjectListDTO getCurrentUserProjectInfo(String userId){

        List<UserProjectEntity> userProjectEntityList = getCurrentUserProjectList(userId);

        List<ProjectEntity> userProjectList = getUserProjectList(userProjectEntityList);

        List<ProjectDetailsDTO> projectDetailList = new ArrayList<>();

        for(ProjectEntity e : userProjectList){

            List<String> members = new ArrayList<>();
            List<UserProjectEntity> entities = userProjectRepository.findByProject(e);
            for(UserProjectEntity entity :entities){
                members.add(entity.getUser().getUserName());
            }

            ProjectDetailsDTO projectDetail = ProjectDetailsDTO.builder()
                    .projectId(e.getProjectId())
                    .projectTitle(e.getTitle())
                    .done(e.isDone())
                    .createDate(e.getCreateDate())
                    .members(members)
                    .memberCount(members.size())
                    .build();
            projectDetailList.add(projectDetail);
        }

        return ProjectListDTO.builder().list(projectDetailList).build();

    }


    // 프로젝트 아이디를 던젔을때 모든 로그를 찍는 서비스 구축
    // 프로젝트 아이디를 던졌을때 모든 투두와 로그를 중첩리스트(맵) 형태로 리턴해주는 서비스 구축
    public ProjectDTO getProjectDetails(String projectId){
        ProjectEntity projectEntity = projectRepository.findById(projectId).orElseThrow();

        List<String> members = new ArrayList<>();

        List<UserProjectEntity> entities = userProjectRepository.findByProject(projectEntity);
        for(UserProjectEntity entity : entities){
            members.add(entity.getUser().getUserName());
        }

        List<TodoDTO> todos = new ArrayList<>();
        for(TodoEntity todoEntity : projectEntity.getTodos()){
            List<LogDTO> logs = new ArrayList<>();
            for(LogEntity logEntity : todoEntity.getLogs()){
                LogDTO logDto = LogDTO.builder()
                        .logId(logEntity.getLogId())
                        .title(logEntity.getTitle())
                        .contents(logEntity.getContents())
                        .done(logEntity.isDone())
                        .createDate(logEntity.getCreateDate())
                        .userName(logEntity.getUser().getUserName())
                        .build();
                logs.add(logDto);
            }

            TodoDTO todoDto = TodoDTO.builder()
                    .todoId(todoEntity.getTodoId())
                    .title(todoEntity.getTitle())
                    .createDate(todoEntity.getCreateDate())
                    .done(todoEntity.isDone())
                    .userName(todoEntity.getUser().getUserName())
                    .logs(logs)
                    .build();
            todos.add(todoDto);
        }

        ProjectDTO resultDto = ProjectDTO.builder()
                .projectId(projectEntity.getProjectId())
                .title(projectEntity.getTitle())
                .content(projectEntity.getContents())
                .done(projectEntity.isDone())
                .createDate(projectEntity.getCreateDate())
                .members(members)
                .memberCount(members.size())
                .todos(todos)
                .build();

        return resultDto;
    }



    }
