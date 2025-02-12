package xyz.gvital.app;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


class UserValidatorTest {

    private final UserValidator userValidator = new UserValidator();

    @ParameterizedTest
    @ValueSource(strings = {"", "email", "email.com", "email@com", "email@.com", "email@com."})
    void failInvalidEmail(String email) {
        UserRegistrationDTO user = new UserRegistrationDTO(email, "phoneNumber", "password", "password");
        assertThrows(IllegalArgumentException.class, () -> userValidator.validate(user));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email@example.com", "email.com@test.co", "email1@com.ua"})
    void passValidEmail(String email) {
        UserRegistrationDTO user = new UserRegistrationDTO(email, "phoneNumber", "password", "password");
        assertDoesNotThrow(() -> userValidator.validate(user));
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    void failInvalidPhoneNumber(String phoneNumber) {
        UserRegistrationDTO user = new UserRegistrationDTO("email@test.com", phoneNumber, "password", "password");
        assertThrows(IllegalArgumentException.class, () -> userValidator.validate(user));
    }

    @ParameterizedTest
    @ValueSource(strings = {"111111111"})
    void passValidPhoneNumber(String phoneNumber) {
        UserRegistrationDTO user = new UserRegistrationDTO("email@test.com", phoneNumber, "password", "password");
        assertDoesNotThrow(() -> userValidator.validate(user));
    }

    @ParameterizedTest
    @CsvSource(value = {",password", "password,pass", "password,passw0rd"})
    void failInvalidPassword(String password, String repeatPassword) {
        UserRegistrationDTO user = new UserRegistrationDTO("email@test.com", "phoneNumber", password, repeatPassword);
        assertThrows(IllegalArgumentException.class, () -> userValidator.validate(user));
    }

    @ParameterizedTest
    @CsvSource(value = {"password,password", "password1,password1", "password123,password123"})
    void passValidPassword(String password, String repeatPassword) {
        UserRegistrationDTO user = new UserRegistrationDTO("email@test.com", "phoneNumber", password, repeatPassword);
        assertDoesNotThrow(() -> userValidator.validate(user));
    }
}