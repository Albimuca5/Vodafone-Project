package org.example.vodafoneproject.controllers;


import org.example.vodafoneproject.dtos.AccountSummaryResponse;

import org.example.vodafoneproject.services.AccountSummaryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class AccountSummaryController {

    private final AccountSummaryService service;

    public AccountSummaryController(AccountSummaryService service) {
        this.service = service;
    }

    //This is the endpoint that returns the aggregated balance and usage information for a given user

    @GetMapping("/{id}/account-summary")
        public AccountSummaryResponse getSummary(@PathVariable Long id) {
            return service.getSummary(id);
        }
}

