package com.demo.credit.controller;

import com.demo.credit.service.AccountStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class BalanceController {
    private final AccountStore accountStore;

    public BalanceController(AccountStore accountStore) {
        this.accountStore = accountStore;
    }

    @GetMapping
    public Map<String, Double> getAccounts() {
        return accountStore.getAccounts();
    }
}