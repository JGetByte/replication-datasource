package com.job.piyawut.replicationdatasource.repository;

import com.job.piyawut.replicationdatasource.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    List<User> findSlaveAll();

    void save(User u);
}
