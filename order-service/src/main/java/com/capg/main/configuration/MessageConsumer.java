//package com.capg.main.configuration;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
////import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import com.capg.main.entity.Message;
//
//
//@Component
//public class MessageConsumer {
//
//   @RabbitListener(queues = RConfiguration.QUEUE)
//
//    public void listener(Message message) {
//        System.out.println(message);
//    }
//
//}