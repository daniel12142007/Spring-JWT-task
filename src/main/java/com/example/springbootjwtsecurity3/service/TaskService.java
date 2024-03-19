package com.example.springbootjwtsecurity3.service;

import com.example.springbootjwtsecurity3.dto.response.EmployeeResponse;
import com.example.springbootjwtsecurity3.dto.response.TaskResponse;
import com.example.springbootjwtsecurity3.model.Employee;
import com.example.springbootjwtsecurity3.model.Orders;
import com.example.springbootjwtsecurity3.model.Task;
import com.example.springbootjwtsecurity3.repository.EmployeeRepository;
import com.example.springbootjwtsecurity3.repository.OrdersRepository;
import com.example.springbootjwtsecurity3.repository.TaskRepository;
import com.example.springbootjwtsecurity3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final OrdersRepository ordersRepository;
    private final EmployeeRepository employeeRepository;

    public TaskResponse save(String title, String description, Long employeeId, Long orderId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(RuntimeException::new);
        Orders orders = ordersRepository.findById(orderId).orElseThrow(RuntimeException::new);
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setEmployee(employee);
        task.setOrder(orders);
        return map(taskRepository.save(task));
    }

    public TaskResponse findById(Long id) {
        return map(taskRepository.findById(id).orElseThrow());
    }

    public List<TaskResponse> findAll() {
        return map(taskRepository.findAll());
    }

    public String deleteById(Long id) {
        taskRepository.deleteById(id);
        return "deleted";
    }

    private List<TaskResponse> map(List<Task> tasks) {
        List<TaskResponse> list = new ArrayList<>();
        tasks.forEach(
                a -> {
                    list.add(map(a));
                }
        );
        return list;
    }

    private TaskResponse map(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .employeeName(task.getEmployee().getFirstName())
                .build();
    }
}