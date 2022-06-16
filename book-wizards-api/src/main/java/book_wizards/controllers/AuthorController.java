package book_wizards.controllers;

import book_wizards.domain.AuthorService;
import book_wizards.domain.Result;
import book_wizards.models.Author;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/author")
public class AuthorController {

  private final AuthorService service;


  public AuthorController(AuthorService service) {
    this.service = service;
  }

  @GetMapping
  public List<Author> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Author> findById(@PathVariable int id) {

    Author searched = service.findById(id);
    if(searched == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Author>(searched, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Object> add(@RequestBody Author author) {
    Result<Author> result = service.add(author);
    if (result.isSuccess()) {
      return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }
    return ErrorResponse.build(result);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Author author) {
    if (id != author.getAuthorId()) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    Result<Author> result = service.update(author);
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
