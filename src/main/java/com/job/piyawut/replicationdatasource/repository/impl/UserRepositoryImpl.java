package com.job.piyawut.replicationdatasource.repository.impl;

import com.job.piyawut.replicationdatasource.configuration.annotation.Slave;
import com.job.piyawut.replicationdatasource.model.User;
import com.job.piyawut.replicationdatasource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from " + User.class.getName() + " u", User.class).getResultList();
    }

    @Slave
    @Override
    public List<User> findSlaveAll() {
        return entityManager.createQuery("select u from " + User.class.getName() + " u", User.class).getResultList();
    }

    @Transactional
    @Override
    public void save(User u) {
        entityManager.merge(u);
    }
}
