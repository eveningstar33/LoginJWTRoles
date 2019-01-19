package com.dgs.springbootjwtauth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dgs.springbootjwtauth.entity.Task;
import com.dgs.springbootjwtauth.repos.TaskRepository;

@RestController
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
    @GetMapping("/task")
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
    
    @GetMapping("/task/{id}") 
    public Task getOneTask(@PathVariable long id) {
    	return taskRepository.findById(id).get();
    }
    
    @PostMapping("/task")
    public void addTask(@RequestBody Task task) {
        taskRepository.save(task);
    }
    
    @PutMapping("/task/{id}")
    public void editTask(@PathVariable long id, @RequestBody Task task) {
        Task existingTask = taskRepository.findById(id).get();
        Assert.notNull(existingTask, "Task not found");
        existingTask.setDescription(task.getDescription());
        taskRepository.save(existingTask);
    }
    
    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable long id) {
        Task taskToDel = taskRepository.findById(id).get();
        taskRepository.delete(taskToDel);
    }
	
}
