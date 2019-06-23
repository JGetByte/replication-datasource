package com.job.piyawut.replicationdatasource;

import com.job.piyawut.replicationdatasource.model.User;
import com.job.piyawut.replicationdatasource.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ReplicationDatasourceApplication {


    public static void main(String[] args) {
        SpringApplication.run(ReplicationDatasourceApplication.class, args);
    }

}
