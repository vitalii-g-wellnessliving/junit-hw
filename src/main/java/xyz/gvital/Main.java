package xyz.gvital;

import xyz.gvital.app.UserRegistrationDTO;
import xyz.gvital.app.UserService;
import xyz.gvital.app.UserServiceImpl;
import xyz.gvital.app.UserValidator;
import xyz.gvital.repo.UserRepositoryImpl;

@SuppressWarnings("hideutilityclassconstructor")
public class Main {
    public static void main(String[] args) {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("test@email.com", "123456789", "password", "password");
        UserService userService = new UserServiceImpl(
                new UserRepositoryImpl(),
                new UserValidator()
        );

        userService.registerUser(userRegistrationDTO);

        System.out.println(userService.getUser(1L));
    }
}
