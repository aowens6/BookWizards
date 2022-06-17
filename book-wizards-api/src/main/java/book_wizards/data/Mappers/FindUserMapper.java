package book_wizards.data.Mappers;

import book_wizards.models.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindUserMapper implements RowMapper<AppUser> {

  private final List<String> roles;

  public FindUserMapper(List<String> roles) {
    this.roles = roles;
  }

  @Override
  public AppUser mapRow(ResultSet rs, int i) throws SQLException {
    return new AppUser(
            rs.getInt("app_user_id"),
            rs.getString("username"),
            "",
            false,
            roles);
  }
}
