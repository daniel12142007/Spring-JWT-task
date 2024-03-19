package com.example.springbootjwtsecurity3.service;

import com.example.springbootjwtsecurity3.dto.response.OrderResponse;
import com.example.springbootjwtsecurity3.model.Client;
import com.example.springbootjwtsecurity3.model.Orders;
import com.example.springbootjwtsecurity3.model.enums.StatusOrder;
import com.example.springbootjwtsecurity3.repository.ClientRepository;
import com.example.springbootjwtsecurity3.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;

    private final ClientRepository clientRepository;

    public OrderResponse save(String order, Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(RuntimeException::new);
        Orders orders = new Orders();
        orders.setClient(client);
        orders.setAa(order);
        orders.setStatusOrder(StatusOrder.PROGRESS);
        orders.setOrderDate(LocalDateTime.now());
        return map(ordersRepository.save(orders));
    }

    public OrderResponse findById(Long orderId) {
        return map(ordersRepository.findById(orderId).orElseThrow());
    }

    public List<OrderResponse> findAll() {
        return map(ordersRepository.findAll());
    }

    public String deleted(Long id) {
        ordersRepository.deleteById(id);
        return "deleted";
    }

    private List<OrderResponse> map(List<Orders> orders) {
        List<OrderResponse> list = new ArrayList<>();
        orders.forEach(
                a -> {
                    list.add(map(a));
                }
        );
        return list;
    }

    private OrderResponse map(Orders orders) {
        return OrderResponse.builder()
                .id(orders.getId())
                .clientName(orders.getClient().getName())
                .order(orders.getAa())
                .statusOrder(orders.getStatusOrder())
                .orderDate(orders.getOrderDate())
                .build();
    }
}