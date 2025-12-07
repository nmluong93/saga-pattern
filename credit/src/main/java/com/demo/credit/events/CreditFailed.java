package com.demo.credit.events;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class CreditFailed implements Serializable {
    private String transferId;
    private String reason;
    private Double refundAmount;
    private String refundAccount;

    public CreditFailed(String transferId, String simulatedCreditFailure, Double amount, String fromAccount) {
        this.transferId = transferId;
        this.reason = simulatedCreditFailure;
        this.refundAmount = amount;
        this.refundAccount = fromAccount;
    }
}
