package com.components.validator;

import com.components.models.User;
import com.components.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;


    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");

        if (user.getUsername().length() < 3 || user.getUsername().length() > 15) {
            errors.rejectValue("username", "size.userForm.username");
        }
        if (userService.findByUserName(user.getUsername()) != null) {
            errors.rejectValue("username", "duplicate.userform.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if (user.getPassword().length() < 3 || user.getPassword().length() > 15) {
            errors.rejectValue("password", "size.userForm.password");
        }
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "different.username.password");
        }
    }

}
