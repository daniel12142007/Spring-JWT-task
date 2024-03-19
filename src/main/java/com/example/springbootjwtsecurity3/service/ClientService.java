package com.example.springbootjwtsecurity3.service;

import com.example.springbootjwtsecurity3.dto.response.ClientResponse;
import com.example.springbootjwtsecurity3.model.Client;
import com.example.springbootjwtsecurity3.model.Company;
import com.example.springbootjwtsecurity3.repository.ClientRepository;
import com.example.springbootjwtsecurity3.repository.CompanyRepository;
import com.example.springbootjwtsecurity3.repository.UserRepository;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public ClientResponse save(String name, String email, Long companyId) {
        if (userRepository.existsByEmail(email))
            throw new RuntimeException("это email уже существунт");
        Company company = companyRepository.findById(companyId).orElseThrow(RuntimeException::new);
        Client client = new Client();
        client.setCompany(company);
        client.setName(name);
        client.setEmail(email);
        clientRepository.save(client);
        return map(client);
    }

    public ClientResponse findById(Long id) {
        return map(clientRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    public List<ClientResponse> findAll() {
        return map(clientRepository.findAll());
    }

    public String deleteById(Long id) {
        clientRepository.deleteById(id);
        return "deleted";
    }

    private List<ClientResponse> map(List<Client> clients) {
        List<ClientResponse> list = new ArrayList<>();
        clients.forEach(
                a -> {
                    list.add(map(a));
                }
        );
        return list;
    }

    private ClientResponse map(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .countOrders(clientRepository.findByCount(client.getId()))
                .build();
    }
}