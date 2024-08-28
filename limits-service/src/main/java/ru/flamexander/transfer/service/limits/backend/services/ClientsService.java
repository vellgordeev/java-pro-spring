package ru.flamexander.transfer.service.limits.backend.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.flamexander.transfer.service.limits.backend.dtos.ClientInfoResponse;

@Service
public class ClientsService {

    private final RestTemplate restTemplate;
    private final String clientServiceUrl = "http://localhost:8190/api/v1/clients";

    public ClientsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ClientInfoResponse getClientInfo(Long clientId) {
        String url = String.format("%s/%d", clientServiceUrl, clientId);
        return restTemplate.getForObject(url, ClientInfoResponse.class);
    }
}
