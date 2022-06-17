package book_wizards.data.Mappers;

import book_wizards.models.PublicUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FindUserMapper implements RowMapper<PublicUser> {

  @Override
  public PublicUser mapRow(ResultSet rs, int i) throws SQLException {
    PublicUser user = new PublicUser();
    user.setAppUserId(rs.getInt("app_user_id"));
    user.setUsername(rs.getString("username"));
    return user;
  }
}
