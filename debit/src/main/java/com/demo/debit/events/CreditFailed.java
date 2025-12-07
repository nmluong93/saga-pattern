package com.demo.debit.events;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditFailed implements Serializable {
    private String transferId;
    private String reason;
    private Double refundAmount;
    private String refundAccount;

}
