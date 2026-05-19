package org.example.vodafoneproject.dtos;

import java.math.BigDecimal;
import java.util.Map;

public class AccountSummaryResponse {

    private Long id;

    private double balance;

    private Map<String, UsageItemDto> usage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Map<String, UsageItemDto> getUsage() {
        return usage;
    }

    public void setUsage(Map<String, UsageItemDto> usage) {
        this.usage = usage;
    }
}