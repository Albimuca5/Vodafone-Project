package org.example.vodafoneproject.services;

import org.example.vodafoneproject.dtos.AccountSummaryResponse;
import org.example.vodafoneproject.dtos.UsageItemDto;
import org.example.vodafoneproject.exceptions.ResourceNotFoundException;
import org.example.vodafoneproject.models.UserBalance;
import org.example.vodafoneproject.repositories.UserBalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AccountSummaryServiceTest {

    @Mock
    private UserBalanceRepository repository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AccountSummaryService service;

    @Test
    void shouldReturnAccountSummary() {

        UserBalance user = new UserBalance();
        user.setId(1L);
        user.setBalance(777.7);

        when(repository.findById(1L))
                .thenReturn(Optional.of(user));

        UsageItemDto usage = new UsageItemDto();
        usage.setType("min");
        usage.setCurrentSpent(100);
        usage.setTotal(5899);

        List<UsageItemDto> usageList = List.of(usage);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                ArgumentMatchers.<org.springframework.core.ParameterizedTypeReference<List<UsageItemDto>>>any()
        )).thenReturn(
                new ResponseEntity<>(usageList, HttpStatus.OK)
        );

        AccountSummaryResponse result = service.getSummary(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(777.7, result.getBalance());
        assertEquals("min", result.getUsage().get("min").getType());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.getSummary(1L));
    }

}