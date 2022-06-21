package book_wizards.data;

import book_wizards.App;
import book_wizards.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(MockitoExtension.class)
class AppUserJdbcTemplateRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    AppUserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @Autowired
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findByUsername() {
        String role = "USER";
        AppUser appUser = new AppUser(2,"userName","Tester",false,
                Collections.singletonList(role));
        assertEquals(appUser,repository.findByUsername("userName"));
    }


    @Test
    void findById() {
//        String role = "USER";
//       AppUser appUser = new AppUser(2,"userName","test",false,
//               Collections.singletonList(role));
//       assertEquals(appUser,repository.findById(2));

    }

    @Test
    void findListOfUsersByIds() {

    }


    @Test
    void update() {

    }
}