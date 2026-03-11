package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepo;
    private final UserRepository userRepo;

    public TaskController(TaskRepository taskRepo,
                          UserRepository userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    // ✅ Create Task
    @PostMapping
    public Task createTask(@RequestBody Task task,
                           Authentication authentication) {

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow();

        task.setUser(user);

        return taskRepo.save(task);
    }

    // ✅ Get My Tasks
    @GetMapping
    public List<Task> getMyTasks(Authentication authentication) {

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow();

        return taskRepo.findByUser(user);
    }
}