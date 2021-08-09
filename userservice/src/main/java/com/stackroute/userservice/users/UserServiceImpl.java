package com.stackroute.userservice.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public List<Users> getUsers() {
        return userRepository.findAll();
    }
}
