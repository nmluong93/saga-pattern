package com.demo.credit;

import com.demo.credit.events.CreditCompleted;
import com.demo.credit.events.CreditFailed;
import com.demo.credit.events.DebitCompleted;
import com.demo.credit.service.AccountStore;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CreditEventHandler {

    private final RabbitTemplate rabbitTemplate;
    private final AccountStore accountStore;

    public CreditEventHandler(RabbitTemplate rabbitTemplate, AccountStore accountStore){
        this.rabbitTemplate = rabbitTemplate;
        this.accountStore = accountStore;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_DEBIT_COMPLETED)
    public void handleDebitCompleted(DebitCompleted event) {
        // Simulate a failure for a specific transferId "FAIL-CREDIT"
        if ("FAIL-CREDIT".equals(event.getTransferId())) {
            System.out.println("Handle failed credit in 'Credit service'");
            CreditFailed cf = new CreditFailed(event.getTransferId(), "Simulated credit failure", event.getAmount(),
                    event.getFromAccount());
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.RK_CREDIT_FAILED, cf);
            return;
        }
        accountStore.credit(event.getToAccount(), event.getAmount());

        CreditCompleted cc = new CreditCompleted(event.getTransferId(), event.getFromAccount(), event.getAmount());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.RK_CREDIT_COMPLETED, cc);
    }
}