package book_wizards.data;

import book_wizards.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJPARepository extends JpaRepository<Book, Integer> {

}
