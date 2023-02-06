package com.example.todo.projectapi.service;


import com.example.todo.projectapi.dto.ProjectDetailsDTO;
import com.example.todo.projectapi.dto.ProjectListDTO;
import com.example.todo.projectapi.entity.ProjectEntity;
import com.example.todo.projectapi.exceptions.ProjectNotFoundException;
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






}
