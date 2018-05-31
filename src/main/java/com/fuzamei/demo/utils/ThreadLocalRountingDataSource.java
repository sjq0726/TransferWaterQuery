package com.fuzamei.demo.utils;

import com.fuzamei.demo.datasource.DataSourceTypeManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ThreadLocalRountingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // TODO Auto-generated method stub
        return DataSourceTypeManager.getCustomerType();
    }

}