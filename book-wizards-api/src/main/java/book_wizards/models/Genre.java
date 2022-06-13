package book_wizards.models;

import javax.persistence.*;

@Entity
public class Genre {

  @Id
  private int genreId;
  private String genreName;

  public int getGenreId() {
    return genreId;
  }

  public void setGenreId(int genreId) {
    this.genreId = genreId;
  }

  public String getGenreName() {
    return genreName;
  }

  public void setGenreName(String genreName) {
    this.genreName = genreName;
  }
}
