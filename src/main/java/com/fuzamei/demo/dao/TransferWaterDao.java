package com.fuzamei.demo.dao;

import com.fuzamei.demo.model.DTO.TrandferWaterShowDTO;
import com.fuzamei.demo.model.DTO.TransferWaterDTO;
import com.fuzamei.demo.model.NewTransferWater;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransferWaterDao {

    Integer numQuery1();

    Integer numQuery2();

    Integer countPage(TransferWaterDTO transferWaterDTO);

    void insertTransferWater();

    void deleteTransferWater();

    List<NewTransferWater> selectTransferWater(TransferWaterDTO transferWater);

    //共有多少个对方的名称
    Integer countNum();

    //查询所有的对方的名称
    List<String> seletetACCNAME1();

    //根据对方的名称查询
    List<NewTransferWater> selectTransferWaterByNameList(@Param("list") List<String> ACCNAMEList);

    List<NewTransferWater> selectTransferWaterByNameAndFlag(TrandferWaterShowDTO trandferWaterShowDTO);

    Integer countTransferWaterByNameAndFlag(TrandferWaterShowDTO trandferWaterShowDTO);
}
