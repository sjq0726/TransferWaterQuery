package com.fuzamei.demo.utils;

import java.util.List;

public class ListUtils {

    /**
     * 判断List是否为空
     *
     * @param list
     */
    public static boolean isEmpty(List list) {

        return list == null || list.isEmpty();
    }

    /**
     * 判断List不为空
     *
     * @param list
     */
    public static boolean isNotEmpty(List list) {

        return !isEmpty(list);
    }
}
