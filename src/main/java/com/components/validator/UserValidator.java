package com.components.validator;

import com.components.database.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {

    private static int INPUT_MAX_LENGTH = 15;
    private static int INPUT_MIN_LENGTH = 3;


    @Autowired
    private UserService userService;


    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");

        if (user.getUsername().length() < INPUT_MIN_LENGTH || user.getUsername().length() > INPUT_MAX_LENGTH) {
            errors.rejectValue("username", "size.userForm.username");
        }
        if (userService.findByUserName(user.getUsername()) != null) {
            errors.rejectValue("username", "duplicate.userform.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if (user.getPassword().length() < INPUT_MIN_LENGTH || user.getPassword().length() > INPUT_MAX_LENGTH) {
            errors.rejectValue("password", "size.userForm.password");
        }
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "different.username.password");
        }
    }

}
