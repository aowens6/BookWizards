package book_wizards.domain;

import book_wizards.data.BookJPARepository;
import book_wizards.data.MeetingJPARepository;
import book_wizards.models.Book;
import book_wizards.models.Meeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MeetingServiceTest {

    @Autowired
    MeetingService service;

    @MockBean
    MeetingJPARepository repository;

    @Test
    void findAll() {
        List<Meeting> meetings = new ArrayList<>();
        when(repository.findAll()).thenReturn(meetings);
        assertEquals(meetings, service.findAll());
    }

    @Test
    void findById() {
    }

    @Test
    void add() {

    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}