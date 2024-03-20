package com.example.springbootjwtsecurity3.api;

import com.example.springbootjwtsecurity3.dto.response.ClientResponse;
import com.example.springbootjwtsecurity3.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/client")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class ClientApi {
    private final ClientService clientService;

    @PostMapping("save")
    public ClientResponse save(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam Long companyId) {
        return clientService.save(name, email, companyId);
    }

    @GetMapping("find/by/{id}")
    public ClientResponse findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping("find/all")
    public List<ClientResponse> findAll() {
        return clientService.findAll();
    }

    @DeleteMapping("delete/by/{id}")
    public String deleteById(@PathVariable Long id) {
        return clientService.deleteById(id);
    }
}