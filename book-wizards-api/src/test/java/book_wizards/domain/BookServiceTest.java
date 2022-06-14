package book_wizards.domain;

import book_wizards.data.BookJPARepository;
import book_wizards.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookServiceTest {

    @Autowired
    BookService service;

    @MockBean
    BookJPARepository repository;

    @Test
    void findAll() {
        List<Book> books = new ArrayList<>();
        when(repository.findAll()).thenReturn(books);
        assertEquals(books,service.findAll());
    }

    @Test
    void findById() {
    }

    @Test
    void add() {
        //setup
        Book book = new Book();
        book.setAuthorId(3);
        book.setTitle("The alchemist");


        //behavior tested
        Result<Book> actual = service.add(book);

        //verification
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void update() {
        Book book = new Book();
        book.setBookId(2);
        book.setAuthorId(3);
        book.setTitle("The alchemist");

        when(repository.existsById(2)).thenReturn(true);

        Result<Book> actual = service.update(book);

        assertEquals(ResultType.SUCCESS,actual.getType());
    }

    @Test
    void deleteById() {
    }
}