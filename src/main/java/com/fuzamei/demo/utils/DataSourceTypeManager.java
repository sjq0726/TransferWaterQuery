package com.fuzamei.demo.utils;

//通过 TheadLocal 来保存每个线程选择哪个数据源的标志(key)


import com.alibaba.druid.util.StringUtils;

public class DataSourceTypeManager {

    public static final String DATA_SOURCE_A = "MASTER";
    public static final String DATA_SOURCE_B = "SLAVE";

    private static final ThreadLocal<String> dataSourceTypes=new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        dataSourceTypes.set(customerType);
    }
    public static String getCustomerType() {
        String dataSource = dataSourceTypes.get();
        if (StringUtils.isEmpty(dataSource)) {
            return DATA_SOURCE_A;
        }else {
            return dataSource;
        }
    }
    public static void clearCustomerType() {
        dataSourceTypes.remove();
    }
}