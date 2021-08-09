package com.stackroute.userservice.users;

import java.util.List;

public interface UserService {
    Users saveUser(Users user);
    List<Users> getUsers();
}
