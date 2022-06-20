package book_wizards.data;

import book_wizards.models.AppUser;
import book_wizards.models.PublicUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AppUserRepository {
    @Transactional
    AppUser findByUsername(String username);

    @Transactional
    PublicUser findById(int id);

    @Transactional
    List<PublicUser> findListOfUsersByIds(List<Integer> ids);

    @Transactional
    AppUser create(AppUser user);

    @Transactional
    void update(AppUser user);
}