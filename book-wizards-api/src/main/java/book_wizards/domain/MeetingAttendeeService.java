package book_wizards.domain;

import book_wizards.data.MeetingAttendeeJPARepo;
import book_wizards.models.Genre;
import book_wizards.models.MeetingAttendee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingAttendeeService {

  private final MeetingAttendeeJPARepo repository;

  public MeetingAttendeeService(MeetingAttendeeJPARepo repository) {
    this.repository = repository;
  }

}
