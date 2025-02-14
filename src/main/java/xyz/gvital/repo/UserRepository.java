package xyz.gvital.repo;

import xyz.gvital.core.User;

import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);

    Optional<User> getUser(Long userId);
}
