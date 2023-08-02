package org.kainos.ea.validator;

import org.kainos.ea.exception.InvalidLoginException;
import org.kainos.ea.model.Login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthValidator {
    public boolean isValidLogin(Login login) throws InvalidLoginException {
        if (!isValidEmail(login.getEmail())) {
            throw new InvalidLoginException("Invalid email format");
        }
        if (login.getEmail() == null || login.getEmail().isEmpty()) {
            throw new InvalidLoginException("Email cannot be empty");
        }
        if (login.getPassword() == null || login.getPassword().isEmpty()) {
            throw new InvalidLoginException("Password cannot be empty");
        }
        if (login.getEmail().length() > 64) {
            throw new InvalidLoginException("Email cannot be longer than 64 characters");
        }
        if (login.getPassword().length() > 64) {
            throw new InvalidLoginException("Password cannot be longer than 64 characters");
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
