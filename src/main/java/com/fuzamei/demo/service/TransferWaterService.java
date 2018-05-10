package com.fuzamei.demo.service;

import com.fuzamei.demo.model.DTO.TransferWaterDTO;
import com.fuzamei.demo.model.NewTransferWater;
import com.fuzamei.demo.model.VO.TransferWaterResponseVO;
import com.github.pagehelper.Page;

import java.util.List;

public interface TransferWaterService {

    Integer numQuery1();

    Integer numQuery2();

    Integer countPage(TransferWaterDTO transferWaterDTO);

    void insertTransferWater();

    void deleteTransferWater();

    Page<NewTransferWater> findAll(Integer pn);

    TransferWaterResponseVO selectTransferWater(TransferWaterDTO transferWater,Integer pn);
}
