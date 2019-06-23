package com.job.piyawut.replicationdatasource.configuration;

import com.job.piyawut.replicationdatasource.constant.DataSourceType;

import static com.job.piyawut.replicationdatasource.constant.DataSourceType.SLAVE;

public class DataSourceContext {
    private static ThreadLocal<DataSourceType> CONTEXT = new ThreadLocal<>();

    public static void markSlaveDataSourceContext() {
        CONTEXT.set(SLAVE);
    }

    public static DataSourceType getCurrentDataSourceContext() {
        DataSourceType current =  CONTEXT.get();
        CONTEXT.remove();
        return current;
    }
}