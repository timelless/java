package com.learnkafka.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.controller.LibraryEventsController;
import com.learnkafka.domain.Book;
import com.learnkafka.domain.LibraryEvent;
import com.learnkafka.producer.LibraryEventProducer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibraryEventsController.class)
@AutoConfigureMockMvc
public class LibraryEventControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LibraryEventProducer libraryEventProducer;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void postLibraryEvent() throws Exception {
        Book book = Book.builder()
                .bookId(777)
                .bookAuthor("Lucky man")
                .bookName("777 Book")
                .build();

        LibraryEvent libraryEvent = LibraryEvent.builder()
                .libraryEventId(null)
                .book(book)
                .build();

        String content = objectMapper.writeValueAsString(libraryEvent);
//        Mockito.doNothing().when(libraryEventProducer).sendLibraryEventAsync2(Mockito.isA(LibraryEvent.class));
        Mockito.when(libraryEventProducer.sendLibraryEventAsync2(Mockito.isA(LibraryEvent.class))).thenReturn(null);

        mockMvc.perform(
                post("/v1/library-event")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void postLibraryEvent_4xx() throws Exception {
        Book book = Book.builder()
                .bookId(null)
                .bookAuthor(null)
                .bookName("777 Book")
                .build();

        LibraryEvent libraryEvent = LibraryEvent.builder()
                .libraryEventId(null)
                .book(book)
                .build();

        String content = objectMapper.writeValueAsString(libraryEvent);
//        Mockito.doNothing().when(libraryEventProducer).sendLibraryEventAsync2(Mockito.isA(LibraryEvent.class));
        Mockito.when(libraryEventProducer.sendLibraryEventAsync2(Mockito.isA(LibraryEvent.class))).thenReturn(null);

        String expectedErrorMessage = "book.bookAuthor - must not be blank, book.bookId - must not be null";
        mockMvc.perform(
                        post("/v1/library-event")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string(expectedErrorMessage));
    }
}
