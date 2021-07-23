package com.stackroute.userservice.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1")
public class UserController {

    @GetMapping("/users")
    public String getUsers(){
        return "Hello";
    }
}
