package com.fuzamei.demo.service.impl;

import com.fuzamei.demo.dao.TransferWaterDao;
import com.fuzamei.demo.model.DTO.TransferWaterDTO;
import com.fuzamei.demo.model.VO.TransferWaterResponseVO;
import com.fuzamei.demo.model.TransferWater;
import com.fuzamei.demo.service.TransferWaterService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("transferWaterServiceImpl")
public class TransferWaterServiceImpl implements TransferWaterService {

    @Autowired
    private TransferWaterDao transferWaterDao;


    @Override
    public Page<TransferWater> findAll(Integer pn) {
        Page<TransferWater> transferWaterPage = PageHelper.offsetPage(pn*10,10)
                .doSelectPage(()->transferWaterDao.findAll());
        return transferWaterPage;
    }

    @Override
    public TransferWaterResponseVO selectTransferWater(TransferWaterDTO transferWater,Integer pn) {
        TransferWaterResponseVO responseVO = new TransferWaterResponseVO();
        List<TransferWater> transferWaterList = transferWaterDao.selectTransferWater(transferWater);
        Page<TransferWater> transferWaterPage=PageHelper.offsetPage(pn*10,10)
                .doSelectPage(()->transferWaterDao.selectTransferWater(transferWater));
        responseVO.setTransferWaterPage(transferWaterPage);
        Double totalAMT = 0.0;
        for (TransferWater input:transferWaterList){
            if ("0".equals(input.getFLAG1())){
                Double amt=Double.parseDouble(input.getAMT());
                totalAMT-=amt;
            }else {
                Double amt = Double.parseDouble(input.getAMT());
                totalAMT += amt;
            }
        }
        String message = null ;
        if(totalAMT>=0){
            message="总共转入了"+totalAMT;
        }else {
            message="总共转出了"+(-totalAMT);
        }
        responseVO.setMessage(message);

        return responseVO;
    }
}