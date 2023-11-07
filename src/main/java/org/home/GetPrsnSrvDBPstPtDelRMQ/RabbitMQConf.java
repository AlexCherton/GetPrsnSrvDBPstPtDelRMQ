package org.home.GetPrsnSrvDBPstPtDelRMQ;

import org.slf4j.*;
import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.*;

@Configuration
public class RabbitMQConf {

    Logger logger = LoggerFactory.getLogger(RabbitMQConf.class);
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue myQueue() {
        return new Queue("Queue1");
    }

    @Bean
    SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("Queue1");
        container.setMessageListener(message -> logger.info("Recieved from my Queue1" + new String(message.getBody())));
        return container;
    }
}
