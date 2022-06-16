package book_wizards.domain;

import book_wizards.data.GenreJPARepository;
import book_wizards.models.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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