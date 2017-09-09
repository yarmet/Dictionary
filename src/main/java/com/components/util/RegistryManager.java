package com.components.util;

import com.components.requestModels.RegistryData;
import com.components.requestModels.ResponseData;
import com.components.services.RegistryService;
import com.components.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruslan on 02.12.16.
 */
@Component
public class RegistryManager {

    private static int MAX_LENGTH = 15;

    @Autowired
    private RegistryService registryService;


    public ResponseData registry(RegistryData registryData) {

        List<String> errors = new ArrayList<>();

        // проверяем чтобы логин был не больше 15 символов.
        if (registryData.getUsername().length() > MAX_LENGTH) {
            errors.add("логин длиннее ".concat(Integer.toString(MAX_LENGTH)).concat(" букв"));
        }

        // проверяем чтобы пароль был не больше 15 символов.
        if (registryData.getPassword().length() > MAX_LENGTH) {
            errors.add("пароль длиннее ".concat(Integer.toString(MAX_LENGTH)).concat(" букв"));
        }

        // проверяем чтобы в логине не было пробела.
        if (registryData.getUsername().contains(" ")) {
            errors.add("в логине содержится пробел");
        }

        // проверяем чтобы в пароле не было пробелов.
        if (registryData.getPassword().contains(" ")) {
            errors.add("в пароле содержится пробел");
        }
        // убеждаемся, что введенные пароли совпадают.
        if (!registryData.getPassword().equals(registryData.getRepeat())) {
            errors.add("введенные пароли не совпадают");
        }

        // если была хоть одна ошибка, отсылаем ее.
        if (!errors.isEmpty()) {
            ResponseData responseData = ResponseData.ERROR;
            responseData.setDescription(errors);
            return responseData;
        }

        // заполняем пользователя.
        User user = new User();
        user.setUsername(registryData.getUsername());
        user.setPassword(registryData.getPassword());
        // пробуем зарегистрироваться.

        if (registryService.registryUser(user)) {
            return ResponseData.OK;
        } else {
            errors.add("такой пользователь уже есть");
            ResponseData responseData = ResponseData.ERROR;
            responseData.setDescription(errors);
            return responseData;
        }

    }

}
