package com.example.springbootjwtsecurity3.api;

import com.example.springbootjwtsecurity3.dto.request.EmployeeRequest;
import com.example.springbootjwtsecurity3.dto.response.EmployeeResponse;
import com.example.springbootjwtsecurity3.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class EmployeeApi {
    private final EmployeeService employeeService;

    @PostMapping("save/employee")
    public EmployeeResponse save(@RequestBody EmployeeRequest request,
                                 @RequestParam Long id) {
        return employeeService.saveEmployee(request, id);
    }

    @GetMapping("find/by/{id}")
    public EmployeeResponse findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping("find/all/employee")
    public List<EmployeeResponse> findAll() {
        return employeeService.findAll();
    }

    @DeleteMapping("delete/by/{id}")
    public String deleteById(@PathVariable Long id) {
        return employeeService.deleteById(id);
    }
}