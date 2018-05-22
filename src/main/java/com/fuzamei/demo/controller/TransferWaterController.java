package com.fuzamei.demo.controller;
import com.fuzamei.demo.model.DTO.TrandferWaterShowDTO;
import com.fuzamei.demo.model.DTO.TransferWaterDTO;
import com.fuzamei.demo.model.NewTransferWater;
import com.fuzamei.demo.model.VO.TransferWaterResponseVO;
import com.fuzamei.demo.model.VO.TransferWaterShowVO;
import com.fuzamei.demo.service.impl.TransferWaterServiceImpl;
import com.fuzamei.demo.utils.ExportExcelUtils;
import com.fuzamei.demo.utils.ListUtils;
import com.fuzamei.demo.utils.ResponseData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/TransferWater")
public class TransferWaterController {

    @Resource(name = "transferWaterServiceImpl")
    private TransferWaterServiceImpl transferWaterService;

    /**
     * 根据条件查询,如果查询条件为null，则为查询所有
     *
     * @param transferWater
     * @return
     */
    @RequestMapping(value = "/selectTransferWater", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData selectTransferWater(TransferWaterDTO transferWater, Integer pn) {
        TransferWaterResponseVO responseDTO = transferWaterService.selectTransferWater(transferWater, pn);
        Integer countPage = transferWaterService.countPage(transferWater);
        ResponseData responseData = ResponseData.ok();
        if (responseDTO != null) {
            responseData.putDataValue("countPage", countPage);
            responseData.putDataValue("data", responseDTO);
            return responseData;
        }
        responseData = ResponseData.notFound();
        return responseData;
    }

    /**
     *首页的分页
     */
    @RequestMapping(value = "selectTrangferByName")
    @ResponseBody
    public ResponseData selectTrangferByName(Integer pn){
        List<TransferWaterShowVO> transferWaterShowVOList=transferWaterService.selectTransferWaterByName(pn);
        Integer countPage=transferWaterService.countNum();
        ResponseData responseData=ResponseData.ok();
        if(!(transferWaterShowVOList.isEmpty())){
            responseData.putDataValue("countPage",countPage);
            responseData.putDataValue("data",transferWaterShowVOList);
            return responseData;
        }
        responseData=ResponseData.notFound();
        return responseData;
    }


    @RequestMapping(value = "/showTransferWaterByNameAndFlag")
    @ResponseBody
    public ResponseData showTransferWaterByNameAndFlag(TrandferWaterShowDTO trandferWaterShowDTO, Integer pn) {
        Integer countPage = transferWaterService.countTransferWaterByNameAndFlag(trandferWaterShowDTO);
        List<NewTransferWater> newTransferWaterList = transferWaterService.selectTransferWaterByNameAndFlag(trandferWaterShowDTO,pn);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("countPage", countPage);
        responseData.putDataValue("data", newTransferWaterList);
        return responseData;
    }

    /**
     * 导出首页的流水表，所有的
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "exportTransferWaterAll")
    @ResponseBody
    private ResponseData exportExcelAll(HttpServletRequest request, HttpServletResponse response){
        try {
            TransferWaterShowVO transferWaterShowVO=new TransferWaterShowVO();
            ResponseData responseData=ResponseData.ok();
            // 查出用户数据
            List<TransferWaterShowVO> transferWaterShowVOList = transferWaterService.selectTransferWaterByName();
            String title ="用户流水表";
            String[] rowsName=new String[]{"对方用户名称","转入","转出","总计"};
            List<Object[]>  dataList = new ArrayList<Object[]>();
            Object[] objs = null;
            for (int i = 0; i < transferWaterShowVOList.size(); i++) {
                TransferWaterShowVO data =transferWaterShowVOList.get(i);
                objs = new Object[rowsName.length];
                objs[0] = i;
                objs[1] = data.getACCName();
                objs[2] = data.getAMTIn();
                objs[3] = data.getAMTOut();
                objs[4] = data.getTotal();
                dataList.add(objs);
            }
            //
            ExportExcelUtils ex =new ExportExcelUtils(title, rowsName, dataList,response);
            ex.exportData();
            responseData.putDataValue("message","导出excel表格成功");
            return  responseData;

        } catch (Exception e) {
            e.printStackTrace();
            ResponseData responseData=ResponseData.forbidden();
            responseData.putDataValue("message","导出excel表格失败");
            return responseData;
        }

    }

    /**
     * 导出首页的流水表，当前的页面的
     * @param request
     * @param response
     * @param pn 要导出的是第几页的
     * @return
     */
    @RequestMapping(value = "exportTransferWater")
    @ResponseBody
    private ResponseData exportExcel(HttpServletRequest request, HttpServletResponse response,Integer pn){
        try {
            TransferWaterShowVO transferWaterShowVO=new TransferWaterShowVO();
            ResponseData responseData=ResponseData.ok();
            // 查出用户数据
            List<TransferWaterShowVO> transferWaterShowVOList = transferWaterService.selectTransferWaterByName(pn);
            String title ="用户流水表";
            String[] rowsName=new String[]{"对方用户名称","转入","转出","总计"};
            List<Object[]>  dataList = new ArrayList<Object[]>();
            Object[] objs = null;
            for (int i = 0; i < transferWaterShowVOList.size(); i++) {
                TransferWaterShowVO data =transferWaterShowVOList.get(i);
                objs = new Object[rowsName.length];
                objs[0] = i;
                objs[1] = data.getACCName();
                objs[2] = data.getAMTIn();
                objs[3] = data.getAMTOut();
                objs[4] = data.getTotal();
                dataList.add(objs);
            }
            //
            ExportExcelUtils ex =new ExportExcelUtils(title, rowsName, dataList,response);
            ex.exportData();
            responseData.putDataValue("message","导出excel表格成功");
            return  responseData;

        } catch (Exception e) {
            e.printStackTrace();
            ResponseData responseData=ResponseData.forbidden();
            responseData.putDataValue("message","导出excel表格失败");
            return responseData;
        }
    }

    /**
     * 导出流水详情表
     * @param request
     * @param response
     * @param transferWaterDTO
     * @param pn
     * @return
     */
    @RequestMapping(value = "exportTransferWaterDetails")
    @ResponseBody
    private ResponseData exportExcelDetails(HttpServletRequest request, HttpServletResponse response,TransferWaterDTO transferWaterDTO,Integer pn ){
        try {
            NewTransferWater transferWater=new NewTransferWater();
            ResponseData responseData=ResponseData.ok();
            // 查出用户数据
            TransferWaterResponseVO transferWaterResponseVO = transferWaterService.selectTransferWater(transferWaterDTO,pn);
            String title ="用户流水详情表";
            String[] rowsName=new String[]{"流水编号","对方账户编号","对方账户名称","转账金额","转账余额"};
            List<Object[]>  dataList = new ArrayList<Object[]>();
            Object[] objs = null;
            for (int i = 0; i < transferWaterResponseVO.getTransferWaterPage().size(); i++) {
                NewTransferWater data =transferWaterResponseVO.getTransferWaterPage().get(i);
                objs = new Object[rowsName.length];
                objs[0] = data.getId();
                objs[1] = data.getACCNO2();
                objs[2] = data.getACC_NAME1();
                objs[3] = data.getAMT();
                objs[4] = data.getAMT1();
                dataList.add(objs);
            }
            //
            ExportExcelUtils ex =new ExportExcelUtils(title, rowsName, dataList,response);
            ex.exportData();
            responseData.putDataValue("message","导出excel表格成功");
            return  responseData;

        } catch (Exception e) {
            e.printStackTrace();
            ResponseData responseData=ResponseData.forbidden();
            responseData.putDataValue("message","导出excel表格失败");
            return responseData;
        }
    }

}

