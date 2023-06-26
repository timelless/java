package com.learnkafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.domain.LibraryEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Supplier;

@RestController
@Slf4j
public class LibraryEventProducer {

    @Autowired
    private  KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    // async
    public void sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
        Integer key = libraryEvent.getLibraryEventId();
        String value = objectMapper.writeValueAsString(libraryEvent);

        /*ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.sendDefault(key, value);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @SneakyThrows
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, value, ex);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccess(key, value, result);
            }
        });*/

        CompletableFuture<SendResult<Integer, String>> future = kafkaTemplate.sendDefault(key, value);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                handleSuccess(key, value, result);
            } else {
                handleFailure(key, value, ex);
            }
        });
    }

    // sync
    public SendResult<Integer, String> sendLibraryEventSync(LibraryEvent libraryEvent) throws JsonProcessingException {
        Integer key = libraryEvent.getLibraryEventId();
        String value = objectMapper.writeValueAsString(libraryEvent);
        SendResult<Integer, String> sendResult = null;

        try {
            sendResult = kafkaTemplate.sendDefault(key, value).get();
        } catch (InterruptedException e) {
            log.info("Message WAS NOT (2) sent {}, {} - exception is - ", key, value, e.getMessage());
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.info("Message WAS NOT (3) sent {}, {} - exception is - ", key, value, e.getMessage());
            throw new RuntimeException(e);
        }

        return sendResult;
    }

    public CompletableFuture sendLibraryEventAsync2(LibraryEvent libraryEvent) throws JsonProcessingException {
        Integer key = libraryEvent.getLibraryEventId();
        String value = objectMapper.writeValueAsString(libraryEvent);
        String topic = "library-events";
        ProducerRecord<Integer, String> producerRecord = buildProducerRecord(key, value, topic);

        /*ListenableFuture<SendResult<Integer, String>> listenableFuture = (ListenableFuture<SendResult<Integer, String>>)
//                kafkaTemplate.send(topic, key, value);
                kafkaTemplate.send(producerRecord);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, value, ex);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccess(key, value, result);
            }
        });*/

        CompletableFuture<SendResult<Integer, String>> future =  kafkaTemplate.send(producerRecord);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                handleSuccess(key, value, result);
            } else {
                handleFailure(key, value, ex);
            }
        });

        return future;
    }

    private void handleSuccess(Integer key, String message, SendResult<Integer, String> result) {
        log.info("Message sent {}, {} - partition is {}", key, message, result.getRecordMetadata().partition());
    }

    private void handleFailure(Integer key, String message, Throwable ex) {
        log.info("Message WAS NOT sent {}, {} - exception is - {}", key, message, ex.getMessage());
        try {
            throw ex;
        } catch (Throwable e) {
            log.info("Error in onFailure: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, String topic) {
        List<Header> recordHeaders = List.of(new RecordHeader("event-source", "scanner".getBytes()));

        ProducerRecord producerRecord = new ProducerRecord<Integer, String>(topic, null, key, value, recordHeaders);

        return producerRecord;
    }
}
