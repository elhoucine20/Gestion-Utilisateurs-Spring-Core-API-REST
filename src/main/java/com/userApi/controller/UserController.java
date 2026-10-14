package com.userApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //
@RequestMapping("/users") //
public class UserController {

    @GetMapping // simplifie @RequestMapping(method = RequestMethod.GET)
    public String test(){
        return "Ca march";
    }
}
