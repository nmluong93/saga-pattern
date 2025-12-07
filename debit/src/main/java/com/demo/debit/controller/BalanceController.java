package com.demo.debit.controller;

import java.util.Map;

import com.demo.debit.service.AccountStore;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class BalanceController {
    private final AccountStore accountStore;


    @GetMapping
    public Map<String, Double> getAccounts() {
        return accountStore.getAccounts();
    }
}