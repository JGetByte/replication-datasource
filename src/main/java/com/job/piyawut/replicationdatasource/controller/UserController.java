package com.job.piyawut.replicationdatasource.controller;

import com.job.piyawut.replicationdatasource.exception.DataNotFoundException;
import com.job.piyawut.replicationdatasource.model.User;
import com.job.piyawut.replicationdatasource.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/master/users")
    public List<User> getMasterUsers()
            throws DataNotFoundException {
        List<User> users = userService.findAll();
        log.info("master user {}", users);
        return users;
    }

    @GetMapping(value = "/slave/users")
    public List<User> getSlaveUsers()
            throws DataNotFoundException {

        List<User> users = userService.findSlaveAll();
        log.info("slave user {}", users);
        return users;
    }

    @PostMapping(value = "/master/addUsers")
    public String addMaster() {
        final int id = userService.addUser("master");
        return "Add master user successfully (user_id: " + id + ")";
    }
}