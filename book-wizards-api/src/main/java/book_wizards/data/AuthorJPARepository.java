package book_wizards.data;

import book_wizards.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorJPARepository extends JpaRepository<Author, Integer> {

}
