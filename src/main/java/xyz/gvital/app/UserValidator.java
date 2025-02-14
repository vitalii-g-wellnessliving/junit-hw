package xyz.gvital.app;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserValidator {
    public void validate(UserRegistrationDTO userRegistrationDTO) {
        if (!isValidEmail(userRegistrationDTO.email())) {
            throw new IllegalArgumentException("Email cannot be blank");
        }
        if (!isValidPhoneNumber(userRegistrationDTO.phoneNumber())) {
            throw new IllegalArgumentException("Phone number cannot be blank");
        }
        if (!isValidPassword(userRegistrationDTO.password(), userRegistrationDTO.repeatPassword())) {
            throw new IllegalArgumentException("Password cannot be blank or passwords do not match");
        }
    }

    boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        if (email.isBlank()) {
            return false;
        }
        return email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return !phoneNumber.isBlank();
    }

    boolean isValidPassword(String password, String repeatPassword) {
        if (password == null) {
            return false;
        }
        return !password.isBlank() && password.equals(repeatPassword);
    }
}
