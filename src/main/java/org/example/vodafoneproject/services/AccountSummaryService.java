package org.example.vodafoneproject.services;


import org.example.vodafoneproject.dtos.AccountSummaryResponse;
import org.example.vodafoneproject.dtos.UsageItemDto;
import org.example.vodafoneproject.exceptions.ResourceNotFoundException;
import org.example.vodafoneproject.models.UserBalance;
import org.example.vodafoneproject.repositories.UserBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountSummaryService {

    @Autowired
    private UserBalanceRepository repository;

    private RestTemplate restTemplate = new RestTemplate();

    public AccountSummaryResponse getSummary(Long id) {

        UserBalance user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // using my own endpoint from ExternalUsageController as a mimic external api just to retrieve data
        String url = "http://localhost:8080/usage/" + id;

        List<UsageItemDto> usageList =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<UsageItemDto>>() {}
                ).getBody();

        Map<String, UsageItemDto> usageMap = new HashMap<>();

        for (UsageItemDto item : usageList) {
            usageMap.put(item.getType(), item);
        }

        AccountSummaryResponse response =
                new AccountSummaryResponse();

        response.setId(user.getId());
        response.setBalance(user.getBalance());
        response.setUsage(usageMap);

        return response;
    }
}