package book_wizards.controllers;

import book_wizards.domain.MeetingService;
import book_wizards.domain.Result;
import book_wizards.models.Author;
import book_wizards.models.Meeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/meeting")
public class MeetingController {

  private  final MeetingService service;

  public MeetingController(MeetingService service) {
    this.service = service;
  }

  @GetMapping
  public List<Meeting> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Meeting findById(@PathVariable int id) {
    return service.findById(id);
  }

  @PostMapping
  public ResponseEntity<Object> add(@RequestBody Meeting meeting) {
    Result<Meeting> result = service.add(meeting);
    if (result.isSuccess()) {
      return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }
    return ErrorResponse.build(result);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Meeting meeting) {
    if (id != meeting.getMeetingId()) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    Result<Meeting> result = service.update(meeting);
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