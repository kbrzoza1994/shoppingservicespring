package org.shoppingservicev2.controller;

import org.shoppingservicev2.service.Response;
import org.shoppingservicev2.service.UserCrud;
import org.shoppingservicev2.service.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    UserCrud userCrud;

    @RequestMapping("/create")
    public String createUser(@ModelAttribute UserDto userDto) {
        return userCrud.createUser(userDto).getMessage();
    }

    @RequestMapping("login")
    public String SignIn(@ModelAttribute UserDto userDto){
        return userCrud.signIn(userDto).getMessage();
    }

    @RequestMapping("/find")
    public String findByLogin(@RequestParam(name = "login") String login){
        Response response = userCrud.findUserId(login);

        if (!response.isSuccess())
            return login + " does not exist";
        return response.getMessage();
    }
}
