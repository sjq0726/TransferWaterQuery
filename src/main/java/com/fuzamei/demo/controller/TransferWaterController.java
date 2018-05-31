package com.fuzamei.demo.controller;
import com.fuzamei.demo.model.DTO.TrandferWaterShowDTO;
import com.fuzamei.demo.model.DTO.TransferWaterDTO;
import com.fuzamei.demo.model.DTO.TransferWaterMainDTO;
import com.fuzamei.demo.model.NewTransferWater;
import com.fuzamei.demo.model.Role;
import com.fuzamei.demo.model.User;
import com.fuzamei.demo.model.VO.TransferWaterResponseVO;
import com.fuzamei.demo.model.VO.TransferWaterShowVO;
import com.fuzamei.demo.service.impl.RoleServiceImpl;
import com.fuzamei.demo.service.impl.TransferWaterServiceImpl;
import com.fuzamei.demo.service.impl.UserServiceImpl;
import com.fuzamei.demo.utils.CustomDateConverter;
import com.fuzamei.demo.utils.ExportExcelUtils;
import com.fuzamei.demo.utils.ListUtils;
import com.fuzamei.demo.utils.ResponseData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/TransferWater")
public class TransferWaterController {

    @Resource(name = "transferWaterServiceImpl")
    private TransferWaterServiceImpl transferWaterService;

    @Resource(name = "userServiceImpl")
    private UserServiceImpl userService;

    @Resource(name = "roleServiceImpl")
    private RoleServiceImpl roleService;

    /**
     *首页的分页
     */
    @RequestMapping(value = "selectTrangferByName")
    @ResponseBody
    public ResponseData selectTrangferByName(HttpServletRequest request,TransferWaterMainDTO transferWaterMainDTO,Integer pn){
        List<TransferWaterShowVO> transferWaterShowVOList=null;
        ResponseData responseData=ResponseData.ok();
        Integer countPage=transferWaterService.countNum();
        String token=request.getHeader("token");
        User user=userService.findUserByToken(token);
        Role role=roleService.selectRoleByName(user.getUsername());

        if(role !=null) {
            if (role.getDegree().equals("1") || role.getDegree().equals("2")) {
                if (transferWaterMainDTO.getACCName()==null) {
                    transferWaterShowVOList = transferWaterService.selectTransferWaterByName(pn);
                    responseData.putDataValue("countPage", countPage);
                } else {
                    transferWaterShowVOList = transferWaterService.selectTransferWaterByName(transferWaterMainDTO);
                }
            } else if (role.getDegree().equals("3")) {
                transferWaterMainDTO.setACCName(role.getName());
                transferWaterShowVOList = transferWaterService.selectTransferWaterByName(transferWaterMainDTO);
            } else {
                responseData = new ResponseData(210, "没有权限");
                return responseData;
            }
        }
        if(transferWaterShowVOList!=null){
            responseData.putDataValue("data",transferWaterShowVOList);
            return responseData;
        }else {
            responseData = ResponseData.notFound();
            return responseData;
        }
    }


    @RequestMapping(value = "/showTransferWaterByNameAndFlag")
    @ResponseBody
    public ResponseData showTransferWaterByNameAndFlag(TrandferWaterShowDTO trandferWaterShowDTO, Integer pn) {
        Integer countPage = transferWaterService.countTransferWaterByNameAndFlag(trandferWaterShowDTO);
        List<NewTransferWater> newTransferWaterList = transferWaterService.selectTransferWaterByNameAndFlag(trandferWaterShowDTO, pn);
        if(newTransferWaterList!=null) {
            ResponseData responseData = ResponseData.ok();
            responseData.putDataValue("countPage", countPage);
            responseData.putDataValue("data", newTransferWaterList);
            return responseData;
        }
        ResponseData responseDate=ResponseData.forbidden();
        return responseDate;
    }

    /**
     * 点击查看详情，根据对方账户查询
     *
     * @param transferWater
     * @return
     */
//    @RequestMapping(value = "/selectTransferWater")
//    @ResponseBody
//    public ResponseData selectTransferWater(TransferWaterDTO transferWater, Integer pn) {
//        TransferWaterResponseVO responseDTO = transferWaterService.selectTransferWater(transferWater, pn);
//        Integer countPage = transferWaterService.countPage(transferWater);
//        ResponseData responseData = ResponseData.ok();
//        if (responseDTO != null) {
//            responseData.putDataValue("countPage", countPage);
//            responseData.putDataValue("data", responseDTO);
//            return responseData;
//        }
//        responseData = ResponseData.notFound();
//        return responseData;
//    }

    /**
     * 导出首页的流水表，所有的
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "exportTransferWaterAll")
    private ModelAndView exportExcelAll(HttpServletRequest request, HttpServletResponse response){
        try {
            TransferWaterShowVO transferWaterShowVO=new TransferWaterShowVO();
            TransferWaterMainDTO transferWaterMainDTO=new TransferWaterMainDTO();
            ResponseData responseData=ResponseData.ok();
            String token=request.getHeader("token");
            User user=userService.findUserByToken(token);
            Role role=roleService.selectRoleByName(user.getUsername());
            List<TransferWaterShowVO> transferWaterShowVOList=null;
            // 查出用户数据
            if(role.getDegree().equals("1") || role.getDegree().equals("2")) {
                transferWaterShowVOList = transferWaterService.selectTransferWaterByName();
            }
            else if(role.getDegree().equals("3")){
                transferWaterMainDTO.setACCName(role.getName());
               transferWaterShowVOList= transferWaterService.selectTransferWaterByName(transferWaterMainDTO);
            }

            String title ="用户流水表";
            String[] rowsName=new String[]{"编号","对方用户名称","转入","转出","总计"};
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

        } catch (Exception e) {
            e.printStackTrace();
        }
         return null;
    }


    /**
     * 导出流水详情表
     * @param request
     * @param response
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "exportTransferWaterDetails")
    private ModelAndView exportExcelDetails(HttpServletRequest request, HttpServletResponse response,TrandferWaterShowDTO trandferWaterShowDTO ){
        try {
            NewTransferWater transferWater=new NewTransferWater();
            ResponseData responseData=ResponseData.ok();
            // 查出用户数据
            List<NewTransferWater> newTransferWaterList = transferWaterService.selectTransferWaterByNameAndFlag(trandferWaterShowDTO);
            String title =null;
            String[] rowsName=new String[]{"流水编号","对方账户编号","对方账户名称","状态","转账金额","余额","转账时间","创建时间"};
            List<Object[]>  dataList = new ArrayList<Object[]>();
            Object[] objs = null;
            for (int i = 0; i < newTransferWaterList.size(); i++) {
                CustomDateConverter customDateConverter=new CustomDateConverter();
                NewTransferWater data =newTransferWaterList.get(i);
                title=data.getACC_NAME1()+"流水详情表";
                objs = new Object[rowsName.length];
                objs[0] = data.getId();
                objs[1] = data.getACCNO2();
                objs[2] = data.getACC_NAME1();
                if(data.getFLAG1().equals("0")){
                    data.setFLAG1("转出");
                }else{
                    data.setFLAG1("转入");
                }
                objs[3] =data.getFLAG1();
                objs[4] = data.getAMT();
                objs[5] = data.getAMT1();
                objs[6] = customDateConverter.convert(data.getTran_date());
                objs[7] = customDateConverter.convert(data.getCreate_date());
                dataList.add(objs);
            }
            //
            ExportExcelUtils ex =new ExportExcelUtils(title, rowsName, dataList,response);
            ex.exportData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

