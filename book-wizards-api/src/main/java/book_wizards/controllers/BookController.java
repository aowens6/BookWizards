package book_wizards.controllers;

import book_wizards.domain.BookService;
import book_wizards.domain.Result;
import book_wizards.models.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/book")
public class BookController {

  private final BookService service;


  public BookController(BookService service) {
    this.service = service;
  }

  @GetMapping
  public List<Book> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Book findById(@PathVariable int id) {
    return service.findById(id);
  }

  @PostMapping
  public ResponseEntity<Object> add(@RequestBody Book book) {
    Result<Book> result = service.add(book);
    if (result.isSuccess()) {
      return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }
    return ErrorResponse.build(result);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Book book) {
    if (id != book.getBookId()) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    Result<Book> result = service.update(book);
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
