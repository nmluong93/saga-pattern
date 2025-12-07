package com.demo.credit.events;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class DebitCompleted implements Serializable {
    private String transferId;
    private String fromAccount;
    private String toAccount;
    private Double amount;
}
