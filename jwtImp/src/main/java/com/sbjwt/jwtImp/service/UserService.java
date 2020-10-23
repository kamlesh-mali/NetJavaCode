package com.sbjwt.jwtImp.service;


import com.sbjwt.jwtImp.model.User;
import com.sbjwt.jwtImp.model.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(int id);

    User findOne(String username);

    User findById(int id);

    UserDto update(UserDto userDto);
}