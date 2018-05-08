package com.fuzamei.demo.utils;

import com.fuzamei.demo.controller.TransferWaterController;
import com.fuzamei.demo.service.TransferWaterService;
import com.fuzamei.demo.service.impl.TransferWaterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TimedTask {

    private TransferWaterServiceImpl transferWaterService;


    public TransferWaterService getTransferWaterService() {
        return transferWaterService;
    }

    @Autowired
    public void setTransferWaterService(TransferWaterServiceImpl transferWaterService) {
        this.transferWaterService = transferWaterService;
    }

    public void change(){
        transferWaterService.task();
    }
}
