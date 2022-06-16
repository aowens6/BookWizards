package book_wizards.domain;

import book_wizards.data.AuthorJPARepository;
import book_wizards.models.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AuthorServiceTest {

    @Autowired
    AuthorService service;

    @MockBean
    AuthorJPARepository repository;

    @Test
    void findAll() {
        List<Author> authors = new ArrayList<>();
        when(repository.findAll()).thenReturn(authors);
        assertEquals(authors,service.findAll());
    }

    @Test
    void findById() {
        Author author = new Author();
        when(repository.findById(3)).thenReturn(Optional.of(author));
        assertEquals(author,service.findById(3));
    }

    @Test
    void add() {
        //setup
        Author author = new Author();
        author.setFirstName("Test");
        author.setLastName("Tester");

        //behavior tested
        Result<Author> actual = service.add(author);

        //output verification
        assertEquals(ResultType.SUCCESS,actual.getType());
    }

    @Test
    void shouldNotAddMissing(){
        //setup
        Author author = new Author();
        author.setFirstName(" ");
        author.setLastName(" ");

        //behavior being tested
        Result<Author> actual = service.add(author);

        //verify output
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void update() {
        //setup
        Author author = new Author();
        author.setAuthorId(2);
        author.setFirstName("Test");
        author.setLastName("Tester");

        when(repository.existsById(2)).thenReturn(true);

        //behavior being tested
        Result<Author> actual = service.update(author);

        //verify output
        assertEquals(ResultType.SUCCESS,actual.getType());
    }

    @Test
    void shouldNotUpdateMissing(){
        //setup
        Author author = new Author();
        author.setAuthorId(3);
        author.setFirstName(" ");

        //behavior tested
        Result<Author> actual = service.update(author);

        //verify output
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void deleteById() {
        when(repository.existsById(2)).thenReturn(true);
        assertTrue(service.deleteById(2));
    }

    @Test
    void shouldNotDelete(){
        when(repository.existsById(2)).thenReturn(false);
        assertFalse(service.deleteById(2));
    }

}