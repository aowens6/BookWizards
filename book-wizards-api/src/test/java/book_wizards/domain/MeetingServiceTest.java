package book_wizards.domain;

import book_wizards.data.BookJPARepository;
import book_wizards.data.MeetingJPARepository;
import book_wizards.models.Book;
import book_wizards.models.Meeting;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
        when(repository.findById(3)).thenReturn(Optional.of(meeting));
        assertEquals(meeting, service.findById(3));
    }

    @Test
    void shouldFindById(){

    }

    @Test
    void add() {
        Meeting meeting = new Meeting();
        meeting.setGroupName("bookWizards");
        meeting.setBookId(3);
        meeting.setOrganizerId(4);
        meeting.setStartDateTime(LocalDateTime.now().plusHours(1));
        meeting.setEndDateTime(LocalDateTime.now().plusHours(2));

        Result<Meeting> actual =  service.add(meeting);

        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotAddMissing(){
        Meeting meeting = new Meeting();
        meeting.setGroupName(" ");
        meeting.setOrganizerId(3);
        meeting.setStartDateTime(LocalDateTime.now().plusHours(1));
        meeting.setEndDateTime(LocalDateTime.now().plusHours(2));

        Result<Meeting> actual = service.add(meeting);

        assertEquals(ResultType.INVALID,actual.getType());
    }

    @Test
    void update() {

        Meeting meeting = new Meeting();
        meeting.setMeetingId(2);
        meeting.setGroupName("Test");
        meeting.setBookId(3);
        meeting.setOrganizerId(3);
        meeting.setStartDateTime(LocalDateTime.now().plusHours(1));
        meeting.setEndDateTime(LocalDateTime.now().plusHours(2));

        when(repository.existsById(2)).thenReturn(true);

        Result<Meeting> actual = service.update(meeting);

        assertEquals(ResultType.SUCCESS,actual.getType());

    }

    @Test
    void shouldNotUpdate(){
        Meeting meeting = new Meeting();
        meeting.setMeetingId(3);
        meeting.setGroupName(" ");
        meeting.setStartDateTime(LocalDateTime.now());
        meeting.setEndDateTime(LocalDateTime.now());

        when(repository.existsById(3)).thenReturn(false);

        Result<Meeting> actual = service.update(meeting);

        assertEquals(ResultType.INVALID, actual.getType());
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