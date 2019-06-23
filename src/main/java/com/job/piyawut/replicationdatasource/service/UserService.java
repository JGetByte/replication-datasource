package com.job.piyawut.replicationdatasource.service;

import com.job.piyawut.replicationdatasource.configuration.annotation.Slave;
import com.job.piyawut.replicationdatasource.model.User;
import com.job.piyawut.replicationdatasource.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private Logger log = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {

        final List<User> users = new ArrayList<>();

        log.info(" --- before calling user repository --- ");
        userRepository.findAll()
                .iterator().forEachRemaining(users::add);
        log.info(" --- after calling user repository --- ");
        return users;
    }

    public List<User> findSlaveAll() {

        final List<User> users = new ArrayList<>();

        userRepository.findSlaveAll()
                .iterator().forEachRemaining(users::add);
        return users;
    }

    public int addUser(String name) {

        List<User> users = findAll();
        int id = users.size() + 1;

        User user = new User();
        user.setUserName(name +"_" + id);
        user.setFirstName(name + "_firstName_" + id);
        user.setLastName( name + "_lastName_" + id);
        userRepository.save(user);

        return id;
    }
}
