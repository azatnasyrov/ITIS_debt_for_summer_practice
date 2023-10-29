package ru.itis.taxi.mappers;

import ru.itis.taxi.dto.SignUpForm;
import ru.itis.taxi.models.User;


public class Mappers {
    public static User signUpFormUser(SignUpForm signUpForm) {
        return new User(signUpForm.getEmail(), signUpForm.getPassword(), signUpForm.getFirstName(), signUpForm.getLastName());
    }
}
