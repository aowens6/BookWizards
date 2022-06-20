package book_wizards.data;

import book_wizards.data.Mappers.MeetingAttendeeMapper;
import book_wizards.models.MeetingAttendee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MeetingAttendeeJDBCRepo implements MeetingAttendeeRepository{

  private final JdbcTemplate jdbcTemplate;

  public MeetingAttendeeJDBCRepo(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  @Override
  public List<Integer> findAttendeesByMeetingId(int meetingId) {

    final String sql = "select * from meeting_attendee where meeting_id = ?;";

    return jdbcTemplate.query(sql, new MeetingAttendeeMapper(), meetingId);

  }

  @Override
  public boolean create(MeetingAttendee meetingAttendee) {

    final String sql = "insert into meeting_attendee values (?,?);";

    return jdbcTemplate.update(sql, meetingAttendee.getMeetingId(), meetingAttendee.getAttendeeId()) > 0;
  }

  @Override
  public boolean deleteAllByMeetingId(int meetingId) {
    final String sql = "delete * from meeting_attendee where meeting_id = ?;";

    return jdbcTemplate.update(sql, meetingId) > 0;
  }

  @Override
  public boolean removeAttendeeFromMeeting(MeetingAttendee meetingAttendee) {

    final String sql = "delete from meeting_attendee where meeting_id = ? and app_user_id = ?;";
    return jdbcTemplate.update(sql, meetingAttendee.getMeetingId(), meetingAttendee.getAttendeeId()) > 0;

  }

}
