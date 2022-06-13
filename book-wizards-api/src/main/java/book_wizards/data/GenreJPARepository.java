package book_wizards.data;

import book_wizards.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreJPARepository extends JpaRepository<Genre, Integer> {

}
