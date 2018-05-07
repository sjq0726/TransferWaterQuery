package com.fuzamei.demo.dao;

import com.fuzamei.demo.model.DTO.TransferWaterDTO;
import com.fuzamei.demo.model.TransferWater;

import java.util.List;

public interface TransferWaterDao {

    List<TransferWater> findAll();

    List<TransferWater> selectTransferWater(TransferWaterDTO transferWater);
}
