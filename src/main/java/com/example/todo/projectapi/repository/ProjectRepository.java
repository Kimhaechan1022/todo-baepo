package com.example.todo.projectapi.repository;

import com.example.todo.projectapi.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {

//    @Override
//    @Query("select")
//    List<ProjectEntity> findById(String projectId);
}
