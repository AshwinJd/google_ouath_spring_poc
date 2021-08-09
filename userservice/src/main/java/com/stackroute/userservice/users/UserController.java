package com.stackroute.userservice.users;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Users> createNewUser(@RequestBody Users user) {
        Users userdata = userService.saveUser(user);
        return new ResponseEntity<>(userdata, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> usersList= userService.getUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}
