package xyz.gvital.app;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
    }
}
