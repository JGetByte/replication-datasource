package com.job.piyawut.replicationdatasource.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.job.piyawut.replicationdatasource.constant.DataSourceType.MASTER;
import static com.job.piyawut.replicationdatasource.constant.DataSourceType.SLAVE;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean
    @DependsOn({"masterDataSource", "slaveDataSource", "routingDataSource"})
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(routingDataSource());
    }

    @Bean
    public DataSource routingDataSource() {
        DataSource masterDS = masterDataSource();
        DataSource slaveDS = slaveDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(MASTER, masterDS);
        dataSourceMap.put(SLAVE, slaveDS);

        RoutingDataSourceManager routingDataSourceManager = new RoutingDataSourceManager();
        routingDataSourceManager.setTargetDataSources(dataSourceMap);
        routingDataSourceManager.setDefaultTargetDataSource(masterDS);

        return routingDataSourceManager;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.master.datasource")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean()
    @ConfigurationProperties(prefix = "spring.slave.datasource")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }




}
