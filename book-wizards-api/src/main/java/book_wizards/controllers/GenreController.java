package book_wizards.controllers;

import book_wizards.domain.GenreService;
import book_wizards.domain.Result;
import book_wizards.models.Book;
import book_wizards.models.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/genre")
public class GenreController {

  private final GenreService service;


  public GenreController(GenreService service) {
    this.service = service;
  }

  @GetMapping
  public List<Genre> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Genre findById(@PathVariable int id) {
    return service.findById(id);
  }

  @PostMapping
  public ResponseEntity<Object> add(@RequestBody Genre genre) {
    Result<Genre> result = service.add(genre);
    if (result.isSuccess()) {
      return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }
    return ErrorResponse.build(result);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Genre genre) {
    if (id != genre.getGenreId()) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    Result<Genre> result = service.update(genre);
    if (result.isSuccess()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return ErrorResponse.build(result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable int id) {
    if (service.deleteById(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
