package com.imatalk.searchservice.listener;

import com.imatalk.searchservice.events.NewMessageEvent;
import com.imatalk.searchservice.events.NewRegisteredUserEvent;
import com.imatalk.searchservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@Slf4j
@RequiredArgsConstructor
public class AppEventListener {

    private final static String NEW_MESSAGE_TOPIC = "new-message";
    private final static String NEW_REGISTERED_USER_TOPIC = "new-registered-user";

    private final SearchService searchService;


    @KafkaListener(topics = NEW_MESSAGE_TOPIC, containerFactory = "appEventKafkaListenerContainerFactory")
    public void listenNewMessageEvent(ConsumerRecord<String, NewMessageEvent> event) {
        log.info("Received new message event: {}", event);
        NewMessageEvent newMessageEvent = event.value();

        searchService.indexNewMessage(newMessageEvent);
        //
    }

    @KafkaListener(topics = NEW_REGISTERED_USER_TOPIC, containerFactory = "appEventKafkaListenerContainerFactory")
    public void listenNewRegisteredUserEvent(ConsumerRecord<String, NewRegisteredUserEvent> event) {
        log.info("Received new registered user event: {}", event);
        NewRegisteredUserEvent newRegisteredUserEvent = event.value();

        searchService.indexNewRegisteredUser(newRegisteredUserEvent);
        //
    }
}
