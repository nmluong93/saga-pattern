package com.demo.credit.events;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class CreditCompleted implements Serializable {
    private String transferId;
    private String toAccount;
    private Double amount;

    public CreditCompleted(String transferId, String fromAccount, Double amount) {
        this.transferId = transferId;
        this.toAccount = fromAccount;
        this.amount = amount;
    }
}
