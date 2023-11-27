package com.example.mobile;

import static org.junit.Assert.*;
import org.junit.Test;

public class LoginTestTest {
    LoginTest l = new LoginTest();

    @Test
    public void emptyEmailReturnFalse() {
        boolean result = l.validLoginInput("", "qwerqwer");
        assertFalse(result);
    }

    @Test
    public void emptyPasswordReturnFalse() {
        boolean result = l.validLoginInput("1234@gmail.com", "");
        assertFalse(result);
    }

    @Test
    public void validEmailPasswordReturnsTrue() {
        boolean result = l.validLoginInput(
                "1234@gmail.com",
                "12341234"
        );
        assertTrue(result);
    }

    @Test
    public void notExistPasswordReturnsFalse() {
        boolean result = l.validLoginInput(
                "1234@gmail.com",
                "hihihihi"
        );
        assertFalse(result);
    }

    @Test
    public void notExistEmailReturnsFalse() {
        boolean result = l.validLoginInput(
                "yoyo@gmail.com",
                "12341234"
        );
        assertFalse(result);
    }

}