package xyz.gvital.app;

public record UserRegistrationDTO(String email, String phoneNumber, String password, String repeatPassword) {
}
