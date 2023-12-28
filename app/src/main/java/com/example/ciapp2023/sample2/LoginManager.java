package com.example.ciapp2023.sample2;

import java.util.HashMap;

public class LoginManager {
    private HashMap<String, User> users;

    public LoginManager() {
        this.users = new HashMap<>();
    }

    public void register(String username, String password) throws ValidateFailedException {
        if (!isUsernameValid(username)) {
            throw new ValidateFailedException("Username input is invalid");
        }
        if (!isPasswordValid(password)) {
            throw new ValidateFailedException("Password input is invalid");
        }

        users.put(username, new User(username, password));
    }

    private boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9]{4,}$");
    }

    private boolean isPasswordValid(String password) {
        return password.matches("^[a-zA-Z0-9]{8,}$");
    }

    public User login(String username, String password) throws LoginFailedException {
        if (users.containsKey(username)) {
            if (users.get(username).getPassword().equals(password)) {
                return users.get(username);
            } else {
                throw new LoginFailedException("Password not match");
            }
        } else {
            throw new LoginFailedException("User does not exist");
        }

    }
}