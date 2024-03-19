package com.example.springbootjwtsecurity3.api;

import com.example.springbootjwtsecurity3.dto.response.TaskResponse;
import com.example.springbootjwtsecurity3.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/task")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class TaskApi {
    private final TaskService taskService;

    @PostMapping("save")
    public TaskResponse save(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam Long employeeId,
                             @RequestParam Long orderId) {
        return taskService.save(title, description, employeeId, orderId);
    }

    @GetMapping("find/by/{id}")
    public TaskResponse findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @GetMapping("find/all")
    public List<TaskResponse> findAll() {
        return taskService.findAll();
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        return taskService.deleteById(id);
    }
}