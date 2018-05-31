package com.fuzamei.demo.model.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TrandferWaterShowDTO {

    private String ACCNO2;

    private String ACCName;

    private String FLAG1;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tranDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


}
