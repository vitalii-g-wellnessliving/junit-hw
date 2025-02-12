package xyz.gvital.app;

import xyz.gvital.core.User;
import xyz.gvital.repo.UserRepository;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserValidator validator;

    public UserServiceImpl(UserRepository userRepository, UserValidator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    public UserResponseDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        validator.validate(userRegistrationDTO);

        User user = new User(userRegistrationDTO.email(), userRegistrationDTO.phoneNumber(), userRegistrationDTO.password());
        User savedUser = userRepository.saveUser(user);
        return new UserResponseDTO(savedUser.getId(), savedUser.getEmail(), savedUser.getPhoneNumber());
    }

    public UserResponseDTO getUser(Long userId) {
        User user = userRepository.getUser(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getPhoneNumber());
    }
}
