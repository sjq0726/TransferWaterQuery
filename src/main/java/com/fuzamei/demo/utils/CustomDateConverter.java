package com.fuzamei.demo.utils;

import org.springframework.core.convert.converter.Converter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateConverter implements Converter<Date, String > {

    @Override
    public String convert(Date source) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(source);
    }

}