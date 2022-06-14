package book_wizards.data;

import book_wizards.models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingJPARepository extends JpaRepository<Meeting, Integer> {

}
