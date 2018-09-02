package com.components.controller;


import com.components.database.models.User;
import com.components.service.UserService;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {


    @Autowired
    private UserService userService;


    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public ResponseEntity<?> registration(@RequestBody User user) {
        userService.registryUser(user);
        userService.login(user.getUsername());
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "userIsLogged", method = RequestMethod.POST)
    public ResponseEntity<?> userIsLogged() {
        return ResponseEntity.ok(Utils.userIsLogged());
    }


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome() {
        return "index";
    }

}
