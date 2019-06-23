package com.job.piyawut.replicationdatasource.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspectService {

    private Logger log = LoggerFactory.getLogger(DataSourceAspectService.class);

    @Before("@annotation(com.job.piyawut.replicationdatasource.configuration.annotation.Slave)")
    public void determineSlaveContext(JoinPoint joinPoint) {
        log.info("Set dataSource context as slave, Class: [{}] , Method: [{}]",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName());

        DataSourceContext.markSlaveDataSourceContext();
    }

}
