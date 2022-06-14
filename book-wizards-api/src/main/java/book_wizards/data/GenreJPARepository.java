package book_wizards.data;

import book_wizards.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreJPARepository extends JpaRepository<Genre, Integer> {

}
