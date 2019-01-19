package com.dgs.springbootjwtauth.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgs.springbootjwtauth.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
