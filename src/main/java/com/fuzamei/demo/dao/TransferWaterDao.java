package com.fuzamei.demo.dao;

import com.fuzamei.demo.model.DTO.TransferWaterDTO;
import com.fuzamei.demo.model.NewTransferWater;

import java.util.List;

public interface TransferWaterDao {

    Integer numQuery1();

    Integer numQuery2();

    void insertTransferWater();

    void deleteTransferWater();

    List<NewTransferWater> findAll();

    List<NewTransferWater> selectTransferWater(TransferWaterDTO transferWater);
}
