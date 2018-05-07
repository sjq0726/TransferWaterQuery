package com.fuzamei.demo.service;

import com.fuzamei.demo.model.DTO.TransferWaterDTO;
import com.fuzamei.demo.model.VO.TransferWaterResponseVO;
import com.fuzamei.demo.model.TransferWater;
import com.github.pagehelper.Page;

import java.util.List;

public interface TransferWaterService {

    Page<TransferWater> findAll(Integer pn);

    TransferWaterResponseVO selectTransferWater(TransferWaterDTO transferWater,Integer pn);
}
