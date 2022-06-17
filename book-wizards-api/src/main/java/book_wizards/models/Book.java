package book_wizards.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Book {

  @Id
  private int bookId;

  @NotNull(message = "Book Title is required")
  @NotBlank(message = "Book Title is required")
  private String title;
  private int authorId;
  private int genreId;

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getAuthorId() {
    return authorId;
  }

  public void setAuthorId(int authorId) {
    this.authorId = authorId;
  }

  public int getGenreId() {
    return genreId;
  }

  public void setGenreId(int genreId) {
    this.genreId = genreId;
  }
}
