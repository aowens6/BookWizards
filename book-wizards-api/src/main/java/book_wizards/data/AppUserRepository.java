package book_wizards.data;

import book_wizards.models.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AppUserRepository {
    @Transactional
    AppUser findByUsername(String username);

    @Transactional
    AppUser findById(int id);

    @Transactional
    List<AppUser> findListOfUsersByIds(List<Integer> ids);

    @Transactional
    AppUser create(AppUser user);

    @Transactional
    void update(AppUser user);
}