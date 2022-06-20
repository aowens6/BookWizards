package book_wizards.data.Mappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MeetingAttendeeMapper implements RowMapper<Integer>{

  @Override
  public Integer mapRow(ResultSet rs, int i) throws SQLException {
    return rs.getInt("app_user_id");
  }
}
