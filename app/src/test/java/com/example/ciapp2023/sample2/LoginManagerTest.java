package com.example.ciapp2023.sample2;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;

public class LoginManagerTest {
    private LoginManager loginManager;

    @Before
    public void setUp() throws ValidateFailedException {
        loginManager = new LoginManager();
        loginManager.register("testuser1", "password");
    }

    @Test
    public void testLoginSuccess() throws LoginFailedException, InvalidPasswordException {
        User user = loginManager.login("testuser1", "password");
        assertThat(user.getUsername(), is("testuser1"));
        assertThat(user.getPassword(), is("password"));
    }

    @Test
    public void testLoginWrongPassword() {
        try {
            User user = loginManager.login("testuser1", "1234");
            fail("Expected InvalidPasswordException was not thrown");
        } catch (InvalidPasswordException e) {
        } catch (LoginFailedException e) {
            fail("Unexpected LoginFailedException was thrown");
        }
    }

    @Test
    public void testLoginUnregisteredUser() throws InvalidPasswordException {
        try {
            User user = loginManager.login("iniad", "password");
            fail("Expected LoginFailedException was not thrown");
        } catch (LoginFailedException e) {
        }
    }
}
