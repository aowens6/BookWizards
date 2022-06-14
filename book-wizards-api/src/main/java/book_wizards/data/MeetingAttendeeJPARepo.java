package book_wizards.data;

import book_wizards.models.MeetingAttendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingAttendeeJPARepo extends JpaRepository<MeetingAttendee, Integer> {

}
