package com.fuzamei.demo.service;

import com.fuzamei.demo.model.DTO.TrandferWaterShowDTO;
import com.fuzamei.demo.model.DTO.TransferWaterDTO;
import com.fuzamei.demo.model.DTO.TransferWaterMainDTO;
import com.fuzamei.demo.model.NewTransferWater;
import com.fuzamei.demo.model.VO.TransferWaterResponseVO;
import com.fuzamei.demo.model.VO.TransferWaterShowVO;

import java.util.List;

public interface TransferWaterService {

    Integer numQuery1();

    Integer numQuery2();

    Integer countPage(TransferWaterDTO transferWaterDTO);

    void insertTransferWater();

    void deleteTransferWater();

    Integer countNum();

    TransferWaterResponseVO selectTransferWater(TransferWaterDTO transferWater,Integer pn);

    List<TransferWaterShowVO> selectTransferWaterByName(Integer pn);

    List<TransferWaterShowVO> selectTransferWaterByName(TransferWaterMainDTO transferWaterMainDTO);

    List<TransferWaterShowVO> selectTransferWaterByName();

    List<NewTransferWater> selectTransferWaterByNameAndFlag(TrandferWaterShowDTO trandferWaterShowDTO,Integer pn);

    List<NewTransferWater> selectTransferWaterByNameAndFlag(TrandferWaterShowDTO trandferWaterShowDTO);

    Integer countTransferWaterByNameAndFlag(TrandferWaterShowDTO trandferWaterShowDTO);


}
