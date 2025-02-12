package xyz.gvital.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.gvital.core.User;
import xyz.gvital.repo.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator validator;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void canRegisterUser() {
        when(userRepository.saveUser(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(Math.round(Math.random() * 100));
            return user;
        });
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("email", "phoneNumber", "password", "password");

        UserResponseDTO userResponseDTO = userService.registerUser(userRegistrationDTO);

        assertNotNull(userResponseDTO.id());
        assertEquals(userRegistrationDTO.email(), userResponseDTO.email());
        assertEquals(userRegistrationDTO.phoneNumber(), userResponseDTO.phoneNumber());
    }

    @Test
    void canGetUser() {
        User mockUser = new User("email", "phoneNumber", "password");
        mockUser.setId(1L);
        when(userRepository.getUser(1L)).thenReturn(Optional.of(mockUser));

        UserResponseDTO res = userService.getUser(1L);

        assertEquals(1L, res.id());
        assertEquals("email", res.email());
        assertEquals("phoneNumber", res.phoneNumber());

    }
}