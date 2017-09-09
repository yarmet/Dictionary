package com.components.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BaseController {



    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginGet() {
        return "login_page";
    }


    @RequestMapping(value = "access_denied", method = RequestMethod.GET)
    public String accessDenied() {
        return "access_denied";
    }


    @RequestMapping(value = "registry", method = RequestMethod.GET)
    public String registry() {
        return "registry";
    }


}