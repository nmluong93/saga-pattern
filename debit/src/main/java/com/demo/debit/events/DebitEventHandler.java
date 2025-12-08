package com.demo.debit.events;

import com.demo.debit.RabbitMQConfig;
import com.demo.debit.service.AccountStore;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DebitEventHandler {

    private final RabbitTemplate rabbitTemplate;
    private final AccountStore accountStore;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_TRANSFER_REQUESTED)
    public void handleTransferRequest(TransferRequested request) {
        boolean success = accountStore.debit(request.getFromAccount(), request.getAmount());
        if (success) {
            DebitCompleted event = new DebitCompleted(request.getTransferId(), request.getFromAccount(),
                    request.getToAccount(), request.getAmount());
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.RK_DEBIT_COMPLETED, event);
        } else {
            DebitFailed event = new DebitFailed(request.getTransferId(),
                    "Insufficient balance in account: " + request.getFromAccount());
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.RK_DEBIT_FAILED, event);
        }
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CREDIT_FAILED)
    public void handleCreditFailed(CreditFailed event) {
        System.out.println("Received Credit Failed so doing refunding in Debit service " + event);
        accountStore.credit(event.getRefundAccount(), event.getRefundAmount());
    }
}