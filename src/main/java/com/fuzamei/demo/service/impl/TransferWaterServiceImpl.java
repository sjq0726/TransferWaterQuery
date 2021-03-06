package com.fuzamei.demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.fuzamei.demo.dao.TransferWaterDao;
import com.fuzamei.demo.model.DTO.TrandferWaterShowDTO;
import com.fuzamei.demo.model.DTO.TransferWaterDTO;
import com.fuzamei.demo.model.DTO.TransferWaterMainDTO;
import com.fuzamei.demo.model.NewTransferWater;
import com.fuzamei.demo.model.VO.TransferWaterResponseVO;
import com.fuzamei.demo.model.VO.TransferWaterShowVO;
import com.fuzamei.demo.service.TransferWaterService;
import com.fuzamei.demo.datasource.DataSourceTypeManager;
import com.fuzamei.demo.utils.ListUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service("transferWaterServiceImpl")
public class TransferWaterServiceImpl implements TransferWaterService{

    org.slf4j.Logger logger = LoggerFactory.getLogger(TransferWaterService.class);

    @Autowired
    private TransferWaterDao transferWaterDao;


    @Override
    public Integer numQuery1() {
        return transferWaterDao.numQuery1();
    }

    @Override
    public Integer numQuery2() {
        DataSourceTypeManager.setCustomerType(DataSourceTypeManager.DATA_SOURCE_B);
        return transferWaterDao.numQuery2();
    }

    @Override
    public Integer countPage(TransferWaterDTO transferWaterDTO) {
        return transferWaterDao.countPage(transferWaterDTO);
    }

    @Override
    public void insertTransferWater() {
        transferWaterDao.insertTransferWater();
    }
    @Override
    public void deleteTransferWater() {
        transferWaterDao.deleteTransferWater();
    }


    @Override
    public Integer countNum() {
        return transferWaterDao.countNum();
    }

    @Override
    public TransferWaterResponseVO selectTransferWater(TransferWaterDTO transferWater,Integer pn) {
        TransferWaterResponseVO responseVO = new TransferWaterResponseVO();
        List<NewTransferWater> transferWaterList = transferWaterDao.selectTransferWater(transferWater);
        Page<NewTransferWater> transferWaterPage=PageHelper.offsetPage(pn*10,10)
                .doSelectPage(()->transferWaterDao.selectTransferWater(transferWater));
        responseVO.setTransferWaterPage(transferWaterPage);
        return responseVO;
    }

    @Override
    public List<TransferWaterShowVO> selectTransferWaterByName() {
        List<TransferWaterShowVO> transferWaterShowVOList=new ArrayList<TransferWaterShowVO>();
        List<String> accNameList=transferWaterDao.seletetACCNAME1();
        if(ListUtils.isEmpty(accNameList)){
            return transferWaterShowVOList;
        }
        List<NewTransferWater> newTransferWaterList=transferWaterDao.selectTransferWaterByNameList(accNameList);
        Map<String ,List<NewTransferWater>> stringListMap=newTransferWaterList.stream()
                .collect(Collectors.groupingBy(NewTransferWater::getACC_NAME1));
        for (String accName: accNameList) {
            double in=0.0;
            double out=0.0;
            String message=null;
            List<NewTransferWater> list = stringListMap.get(accName);
            if (ListUtils.isNotEmpty(list)) {
                TransferWaterShowVO transferWaterShowVO=new TransferWaterShowVO();
                transferWaterShowVO.setACCName(accName);
                for (NewTransferWater newTransferWater : list) {
                    if ("0".equals(newTransferWater.getFLAG1())) {
                        Double amtOut = Double.parseDouble(newTransferWater.getAMT());
                        out += amtOut;
                    } else {
                        Double amtIn = Double.parseDouble(newTransferWater.getAMT());
                        in += amtIn;
                    }
                }
                DecimalFormat format=new DecimalFormat("0.00");
                String totalAMTIn=format.format(in);
                String totalAMTOut=format.format(out);
                message = format.format(in-out);
                transferWaterShowVO.setAMTIn(totalAMTIn);
                transferWaterShowVO.setAMTOut(totalAMTOut);
                transferWaterShowVO.setTotal(message);
                transferWaterShowVOList.add(transferWaterShowVO);
            }
        }
        return transferWaterShowVOList;
    }
    /**
     * 根据对方的名称查询
     * @param pn
     * @return
     */
    @Override
    public List<TransferWaterShowVO> selectTransferWaterByName(Integer pn) {
        List<TransferWaterShowVO> transferWaterShowVOList=new ArrayList<TransferWaterShowVO>();
        //        //List<String> accNameList=transferWaterDao.seletetACCNAME1();
        Page<String> accNamePage=PageHelper.offsetPage(pn*10,10).doSelectPage(
                ()->transferWaterDao.seletetACCNAME1());
        if (ListUtils.isEmpty(accNamePage.getResult())){

            return null;
        }
        List<String> nameList = accNamePage.getResult();
        List<NewTransferWater> transferWaterList=transferWaterDao.selectTransferWaterByNameList(nameList);
        Map<String,List<NewTransferWater>> stringListMap = transferWaterList.stream()
                .collect(Collectors.groupingBy(NewTransferWater::getACC_NAME1));
        for ( String ACCNAME : accNamePage) {
            double in=0.0;
            double out=0.0;
            String message=null;
            List<NewTransferWater> list = stringListMap.get(ACCNAME);
            if (ListUtils.isNotEmpty(list)) {
                TransferWaterShowVO transferWaterShowVO=new TransferWaterShowVO();
                transferWaterShowVO.setACCName(ACCNAME);
                for (NewTransferWater newTransferWater : list) {
                    if ("0".equals(newTransferWater.getFLAG1())) {
                        Double amtOut = Double.parseDouble(newTransferWater.getAMT());
                        out += amtOut;
                    } else {
                        Double amtIn = Double.parseDouble(newTransferWater.getAMT());
                        in += amtIn;
                    }
                }
                DecimalFormat format=new DecimalFormat("0.00");
                String totalAMTIn=format.format(in);
                String totalAMTOut=format.format(out);
                message = format.format(in-out);
                transferWaterShowVO.setAMTIn(totalAMTIn);
                transferWaterShowVO.setAMTOut(totalAMTOut);
                transferWaterShowVO.setTotal(message);
                transferWaterShowVOList.add(transferWaterShowVO);
            }
        }
        return transferWaterShowVOList;
    }

    /**
     * 单独的一个用户
     * @param transferWaterMainDTO
     * @return
     */
    @Override
    public List<TransferWaterShowVO> selectTransferWaterByName(TransferWaterMainDTO transferWaterMainDTO) {
        List<TransferWaterShowVO> transferWaterShowVOList=new ArrayList<TransferWaterShowVO>();
        TransferWaterShowVO transferWaterShowVO=null;
        //List<String> accNameList=transferWaterDao.seletetACCNAME1();
        List<NewTransferWater> transferWaterList = transferWaterDao.selectTransferWaterByName(transferWaterMainDTO);
        if (ListUtils.isEmpty(transferWaterList)) {
            return transferWaterShowVOList;
        }
            double in = 0.0;
            double out = 0.0;
            String message = null;
                transferWaterShowVO = new TransferWaterShowVO();
                transferWaterShowVO.setACCName(transferWaterMainDTO.getACCName());
                for (NewTransferWater newTransferWater : transferWaterList) {
                    if ("0".equals(newTransferWater.getFLAG1())) {
                        Double amtOut = Double.parseDouble(newTransferWater.getAMT());
                        out += amtOut;
                    } else {
                        Double amtIn = Double.parseDouble(newTransferWater.getAMT());
                        in += amtIn;
                    }
                }
                DecimalFormat format=new DecimalFormat("0.00");
                String totalAMTIn=format.format(in);
                String totalAMTOut=format.format(out);
                message = format.format(in-out);
                transferWaterShowVO.setAMTIn(totalAMTIn);
                transferWaterShowVO.setAMTOut(totalAMTOut);
                transferWaterShowVO.setTotal(message);
                transferWaterShowVOList.add(transferWaterShowVO);
        return transferWaterShowVOList;
    }


    @Override
    public List<NewTransferWater> selectTransferWaterByNameAndFlag(TrandferWaterShowDTO trandferWaterShowDTO,Integer pn) {
        Page<NewTransferWater> newTransferWaterPage=PageHelper.offsetPage(pn*10,10).doSelectPage(
                ()->transferWaterDao.selectTransferWaterByNameAndFlag(trandferWaterShowDTO));

        return newTransferWaterPage;
    }

    @Override
    public List<NewTransferWater> selectTransferWaterByNameAndFlag(TrandferWaterShowDTO trandferWaterShowDTO) {
        return transferWaterDao.selectTransferWaterByNameAndFlag(trandferWaterShowDTO);
    }

    @Override
    public Integer countTransferWaterByNameAndFlag(TrandferWaterShowDTO trandferWaterShowDTO) {
        return transferWaterDao.countTransferWaterByNameAndFlag(trandferWaterShowDTO);
    }


    public void task() {
        Integer num2=transferWaterDao.numQuery2();
        Integer num1=transferWaterDao.numQuery1();
        if(num1 != num2){
            transferWaterDao.deleteTransferWater();
            transferWaterDao.insertTransferWater();
        }
    }
}

