package com.learnkafka.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.domain.Book;
import com.learnkafka.domain.LibraryEvent;
import com.learnkafka.producer.LibraryEventProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.SettableListenableFuture;
import scala.Int;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// Allows us to mock kafkaTemplate.send
@ExtendWith(MockitoExtension.class)
public class LibraryEventProducerUnitTest {
    @Mock
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Spy
    ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    LibraryEventProducer libraryEventProducer;

    @Test
    void sendLibraryEventAsync2_failure() throws JsonProcessingException, ExecutionException, InterruptedException {
        Book book = Book.builder()
                .bookId(777)
                .bookAuthor("Lucky man")
                .bookName("777 Book")
                .build();

        LibraryEvent libraryEvent = LibraryEvent.builder()
                .libraryEventId(null)
                .book(book)
                .build();

//        SettableListenableFuture future = new SettableListenableFuture();
//        future.setException(new RuntimeException("CUSTOM KAFKA EXCEPTION"));

        CompletableFuture future = new CompletableFuture();
        future.completeExceptionally(new RuntimeException("CUSTOM KAFKA EXCEPTION"));

        Mockito.when(kafkaTemplate.send(Mockito.isA(ProducerRecord.class))).thenReturn(future);
        Assertions.assertThrows(Exception.class, () -> libraryEventProducer.sendLibraryEventAsync2(libraryEvent).get());
    }

    @Test
    void sendLibraryEventAsync2_success() throws JsonProcessingException, ExecutionException, InterruptedException {
        Book book = Book.builder()
                .bookId(777)
                .bookAuthor("Lucky man")
                .bookName("7778 Book")
                .build();

        LibraryEvent libraryEvent = LibraryEvent.builder()
                .libraryEventId(null)
                .book(book)
                .build();

        CompletableFuture future = new CompletableFuture();

        String record = objectMapper.writeValueAsString(libraryEvent);
        ProducerRecord<Integer, String> producerRecord = new ProducerRecord("library-events", libraryEvent.getLibraryEventId(), record);
        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition("library-events", 1), 1, 1, 342, System.currentTimeMillis(), 1, 2);
        SendResult<Integer, String> sendResult = new SendResult<>(producerRecord,recordMetadata);

        future.complete(sendResult);
        Mockito.when(kafkaTemplate.send(Mockito.isA(ProducerRecord.class))).thenReturn(future);


        CompletableFuture completableFuture = libraryEventProducer.sendLibraryEventAsync2(libraryEvent);
        SendResult<Integer, String> sendResult1 = (SendResult<Integer, String>) completableFuture.get();

        assert sendResult1.getRecordMetadata().partition() == 1;
    }
}
