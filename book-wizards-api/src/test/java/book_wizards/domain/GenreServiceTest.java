package book_wizards.domain;

import book_wizards.data.GenreJPARepository;
import book_wizards.models.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GenreServiceTest {

    @Autowired
    GenreService service;

    @MockBean
    GenreJPARepository repository;

    @Test
    void findAll() {
        List<Genre> genres = new ArrayList<>();
        when(repository.findAll()).thenReturn(genres);
        assertEquals(genres, service.findAll());
    }

    @Test
    void findById() {
        Genre genre = new Genre();
        when(repository.findById(anyInt())).thenReturn(Optional.of(genre));
        assertEquals(genre,service.findById(anyInt()));
    }

//    @Test
//    void shouldNotFindById(){
//        Genre genre = new Genre();
//        when(reposito)
//    }

    @Test
    void add() {
        Genre genre = new Genre();
        genre.setGenreName("Test");

        Result<Genre> actual = service.add(genre);

        assertEquals(ResultType.SUCCESS,actual.getType());
    }

    @Test
    void shouNotAddMissing() {
        Genre genre = new Genre();
        genre.setGenreName(" ");

        Result<Genre> actual = service.add(genre);

        assertEquals(ResultType.INVALID,actual.getType());
    }

    @Test
    void update() {
        Genre genre = new Genre();
        genre.setGenreId(2);
        genre.setGenreName("Test");

        when(repository.existsById(2)).thenReturn(true);

        Result<Genre> actual = service.update(genre);

        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing(){
        Genre genre = new Genre();
        genre.setGenreId(3);
        genre.setGenreName(" ");

        Result<Genre> actual = service.update(genre);

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