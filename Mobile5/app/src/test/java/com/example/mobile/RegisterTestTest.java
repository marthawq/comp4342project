package com.example.mobile;

import static org.junit.Assert.*;
import org.junit.Test;

public class RegisterTestTest{
    RegisterTest r = new RegisterTest();

    @Test
    public void emptyEmailReturnFalse() {
        boolean result = r.validRegisterInput("hi", "", "qwerqwer");
        assertFalse(result);
    }

    @Test
    public void validEmailReturnsTrue() {
        boolean result = r.validRegisterInput(
                "Martha",
                "qwer@gmail.com",
                "qwerqwer"
        );
        assertTrue(result);
    }

    @Test
    public void emailAlreadyExistsReturnFalse() {
        boolean result = r.validRegisterInput("Jason", "qwer1234@gmail.com", "qwerqwer");
        assertFalse(result);
    }

    @Test
    public void emptyPasswordReturnFalse() {
        boolean result = r.validRegisterInput("yo", "qwe@gmail.com", "");
        assertFalse(result);
    }

    @Test
    public void emptyUsernameReturnFalse() {
        boolean result = r.validRegisterInput("", "qweqwe@gmail.com", "qwer1234");
        assertFalse(result);
    }

}