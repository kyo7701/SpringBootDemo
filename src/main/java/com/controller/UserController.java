package com.controller;

import com.entity.User;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author:Mr.Cris
 * Date:2017-06-24 22:17
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService service;

    @RequestMapping("/all")
    public List<User> users() {
        return service.selectUser();
    }

}
