package com.example.springbootjwtsecurity3.dto.response;

import com.example.springbootjwtsecurity3.model.enums.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private String order;
    private StatusOrder statusOrder;
    private LocalDateTime orderDate;
    private String clientName;
}