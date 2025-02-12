package xyz.gvital.app;

public interface UserService {
    UserResponseDTO registerUser(UserRegistrationDTO userRegistrationDTO);

    UserResponseDTO getUser(Long userId);
}
