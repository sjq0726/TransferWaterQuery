package com.fuzamei.demo.controller;
import com.fuzamei.demo.model.DTO.TransferWaterDTO;
import com.fuzamei.demo.model.VO.TransferWaterResponseVO;
import com.fuzamei.demo.service.impl.TransferWaterServiceImpl;
import com.fuzamei.demo.utils.ResponseData;
import org.springframework.stereotype.Controller;
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
    private TransferWaterServiceImpl transferWaterService;

    /**
     * 根据条件查询
     * @param transferWater
     * @return
     */
    @RequestMapping(value = "/selectTransferWater",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseData selectTransferWater(TransferWaterDTO transferWater,Integer pn){
        TransferWaterResponseVO responseDTO=transferWaterService.selectTransferWater(transferWater,pn);
        Integer countPage=transferWaterService.countPage(transferWater);
        ResponseData responseData=ResponseData.ok();
        if(responseDTO!=null) {
            responseData.putDataValue("countPage",countPage);
            responseData.putDataValue("data",responseDTO);
            return responseData;
        }
        responseData=ResponseData.forbidden();
        return responseData;
    }

    }

