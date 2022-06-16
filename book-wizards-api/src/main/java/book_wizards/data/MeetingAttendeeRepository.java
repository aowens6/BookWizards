package book_wizards.data;

import book_wizards.models.MeetingAttendee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingAttendeeRepository {

  List<Integer> findAttendeesByMeetingId(int meetingId);

  boolean create(MeetingAttendee meetingAttendee); //signing user up for meeting

}
