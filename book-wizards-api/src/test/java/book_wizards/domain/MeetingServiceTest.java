package book_wizards.domain;

import book_wizards.data.BookJPARepository;
import book_wizards.data.MeetingJPARepository;
import book_wizards.models.Book;
import book_wizards.models.Meeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
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
        Meeting meeting = new Meeting();
        //when(repository.existsById(anyInt())).thenReturn(meeting);
        assertEquals(meeting, service.findById(anyInt()));
    }

    @Test
    void add() {
        Meeting meeting = new Meeting();
        meeting.setDescription("test");
        meeting.setGroupName("bookWizards");
//        meeting.setStartDateTime();
//        meeting.setEndDateTime();

        Result<Meeting> actual =  service.add(meeting);

        assertEquals(ResultType.SUCCESS, actual.getType());

    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
        when(repository.existsById(3)).thenReturn(true);
        assertTrue(service.deleteById(3));
    }

    @Test
    void shouldNotDeleteById(){
        when(repository.existsById(3)).thenReturn(false);
        assertFalse(service.deleteById(3));
    }
}