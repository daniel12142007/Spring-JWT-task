package com.example.springbootjwtsecurity3.api;

import com.example.springbootjwtsecurity3.dto.response.OrderResponse;
import com.example.springbootjwtsecurity3.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class OrdersApi {
    private final OrderService orderService;

    @PostMapping("save")
    public OrderResponse save(@RequestParam String order,
                              @RequestParam Long clientId) {
        return orderService.save(order, clientId);
    }

    @GetMapping("find/by/{id}")
    public OrderResponse findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping("find/all")
    public List<OrderResponse> findAll() {
        return orderService.findAll();
    }

    @DeleteMapping("delete/{id}")
    public String deleteById(@PathVariable Long id) {
        return orderService.deleted(id);
    }
}