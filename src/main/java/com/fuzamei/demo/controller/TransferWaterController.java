package com.fuzamei.demo.controller;
import com.fuzamei.demo.model.DTO.TransferWaterDTO;
import com.fuzamei.demo.model.VO.TransferWaterResponseVO;
import com.fuzamei.demo.model.TransferWater;
import com.fuzamei.demo.service.TransferWaterService;
import com.fuzamei.demo.utils.NewResponseModel;
import com.github.pagehelper.Page;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.List;
@Controller
@RequestMapping("/TransferWater")
public class TransferWaterController {

    @Resource(name = "transferWaterServiceImpl")
    private TransferWaterService transferWaterService;

    private NewResponseModel newResponseModel;

    /**
     * 查询所有的转账流水
     * @return
     */
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public NewResponseModel findAll(Integer  pn){
        Page<TransferWater> transferWaterList=transferWaterService.findAll(pn);
        if(transferWaterList !=null) {
            newResponseModel = new NewResponseModel(200, "查询所有");
            newResponseModel.setData(transferWaterList);
        }
        newResponseModel=new NewResponseModel(400,"查询失败");
        return newResponseModel;

    }


    /**
     * 根据条件查询
     * @param transferWater
     * @return
     */
    @RequestMapping(value = "/selectTransferWater")
    @ResponseBody
    public NewResponseModel selectTransferWater(TransferWaterDTO transferWater,Integer pn){
        TransferWaterResponseVO responseDTO=transferWaterService.selectTransferWater(transferWater,pn);
        if(responseDTO!=null) {
            NewResponseModel newResponseModel = new NewResponseModel(200, "查询成功");
            newResponseModel.setData(responseDTO);
        }
        NewResponseModel newResponseModel=new NewResponseModel(400,"查询失败");
        return newResponseModel;
    }


}
