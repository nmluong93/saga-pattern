package com.demo.credit.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AccountStore {
    private final Map<String, Double> accounts = new ConcurrentHashMap<>(Map.of("C", 20.0, "D", 80.0));

    public Map<String, Double> getAccounts() {
        return accounts;
    }

    public Double getBalance(String acct) {
        return accounts.getOrDefault(acct, 0.0);
    }

    public void updateBalance(String acct, Double newBal) {
        accounts.put(acct, newBal);
    }

    public boolean debit(String acct, Double amount) {
        synchronized (accounts) {
            Double balance = getBalance(acct);
            if (balance >= amount) {
                Double updated = balance - amount;
                updateBalance(acct, updated);
                return true;
            } else {
                return false;
            }
        }
    }

    public void credit(String acct, Double amount) {
        synchronized (accounts) {
            Double balance = getBalance(acct);
            Double updated = balance + amount;
            updateBalance(acct, updated);
        }
    }
}
