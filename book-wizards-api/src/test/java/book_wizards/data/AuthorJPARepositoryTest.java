package book_wizards.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AuthorJPARepositoryTest {

  @Autowired
  AuthorJPARepository repository;

  @Test
  void shouldFind3Authors(){
    var authors = repository.findAll();

    assertEquals(3, authors.size());
  }

}