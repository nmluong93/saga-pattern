package com.demo.debit.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequested implements Serializable {
    private String transferId;
    private String fromAccount;
    private String toAccount;
    private Double amount;
}
