package xyz.gvital.core;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String email;
    private String phoneNumber;
    private String password;

    public User(String email, String phoneNumber, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
