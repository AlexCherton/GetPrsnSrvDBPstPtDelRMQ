package org.home.GetPrsnSrvDBPstPtDelRMQ;

import org.slf4j.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RabbitMQController {
    Logger logger = LoggerFactory.getLogger(RabbitMQConf.class);
    private final AmqpTemplate amqpTemplate;

    public RabbitMQController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate= amqpTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody String message) {
        logger.info("Send to Queue1");
        amqpTemplate.convertAndSend("Queue1", message);
        return ResponseEntity.ok("Success send to my Queue1");
    }
}


