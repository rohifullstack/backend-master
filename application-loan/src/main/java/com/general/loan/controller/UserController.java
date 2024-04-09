package com.general.loan.controller;

import com.general.loan.dto.UserDto;
import com.general.loan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{userId}/{userPassword}", method = RequestMethod.GET,
            headers = "Accept=application/json")
    public UserDto validateUser(@PathVariable("userId") String userId, @PathVariable("userPassword") String userPassword) {
        System.out.println("enter into validateUser");
        return userService.validateUser(userId, userPassword);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST,
            headers = "Accept=application/json")
    public UserDto createUser(@RequestBody UserDto userDto) {
        System.out.println("enter into createUser");
        return userService.createUser(userDto);
    }

    @RequestMapping(value = "/user-list", method = RequestMethod.GET,
            headers = "Accept=application/json")
    public List<UserDto> getAllUser() {
        return userService.getAllUserList();
    }
}
