package com.demo.debit.controller;

import com.demo.debit.RabbitMQConfig;
import com.demo.debit.events.TransferRequested;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final RabbitTemplate rabbitTemplate;

    public TransferController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public String requestTransfer(@RequestBody TransferRequested request) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.RK_TRANSFER_REQUESTED, request);
        return "Transfer requested: " + request.getTransferId();
    }
}