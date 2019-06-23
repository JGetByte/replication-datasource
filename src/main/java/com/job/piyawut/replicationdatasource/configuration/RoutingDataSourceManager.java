package com.job.piyawut.replicationdatasource.configuration;

import com.job.piyawut.replicationdatasource.constant.DataSourceType;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

import static com.job.piyawut.replicationdatasource.constant.DataSourceType.MASTER;
import static com.job.piyawut.replicationdatasource.constant.DataSourceType.SLAVE;

public class RoutingDataSourceManager extends AbstractRoutingDataSource {

    private Logger log = LoggerFactory.getLogger(RoutingDataSourceManager.class);

    @Autowired
    @Qualifier("slaveDataSource")
    DataSource slaveDataSource;

    @Autowired
    @Qualifier("masterDataSource")
    DataSource masterDataSource;

    @Value("${spring.slave.ignore}")
    private boolean ignoreSlave;

    @PostConstruct
    public void init() {
        connectSlave();
    }

    @Override
    protected Object determineCurrentLookupKey() {

        if (ignoreSlave) {
            return MASTER;
        }

        log.info("masterDataSource {}", masterDataSource);
        log.info("slaveDataSource {}", slaveDataSource);

        final HikariDataSource hikariSlaveDataSource = (HikariDataSource) slaveDataSource;
        final DataSourceType current = DataSourceContext.getCurrentDataSourceContext();
        log.info("current : {}", current);

        final boolean isSlaveRunning = hikariSlaveDataSource.isRunning();
        log.info("SLAVE dataSource is running : {}", isSlaveRunning);

        if (SLAVE == current && !isSlaveRunning) {
            log.warn("SLAVE DataSource is down!!! --> using MASTER dataSource");
            return MASTER;
        }
        log.info("finish routing dataSource, current dataSource : {}"
                , current == null ? "MASTER" : current);
        return current;
    }

    private void connectSlave() {
        try {
            if (ignoreSlave) {
                log.info("Ignore SLAVE dataSource");
                return;
            }
            slaveDataSource.getConnection();
        } catch (SQLException e) {
            log.warn("failed to connect SLAVE dataSource");
        }
    }
}
