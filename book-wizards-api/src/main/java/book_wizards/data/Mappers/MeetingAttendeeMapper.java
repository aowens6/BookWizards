package book_wizards.data.Mappers;

import book_wizards.models.AppUser;
import book_wizards.models.MeetingAttendee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MeetingAttendeeMapper implements RowMapper<Integer>{

  @Override
  public Integer mapRow(ResultSet rs, int i) throws SQLException {
    return rs.getInt("app_user_id");
  }
}
