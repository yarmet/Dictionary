package com.components.controller;


import com.components.database.models.User;
import com.components.service.UserService;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public ResponseEntity<?> registration(@RequestParam("username") String username,@RequestParam("password") String password ) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userService.registryUser(user);
        userService.login(user.getUsername());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/registry", method = RequestMethod.GET)
    public String registration() {
        return "registry";
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
