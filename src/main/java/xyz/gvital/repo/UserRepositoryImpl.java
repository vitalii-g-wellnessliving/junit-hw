package xyz.gvital.repo;

import lombok.NoArgsConstructor;
import xyz.gvital.core.User;

import java.util.Optional;

@NoArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private User user;

    public User saveUser(User u) {
        // Save user to database and assign an id
        u.setId(1L);
        this.user = u;
        return u;
    }

    public Optional<User> getUser(Long userId) {
        return Optional.of(user);
    }

}
