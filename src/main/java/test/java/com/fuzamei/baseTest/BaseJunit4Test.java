package com.fuzamei.baseTest;

import com.fuzamei.demo.controller.TransferWaterController;
import com.fuzamei.demo.model.VO.TransferWaterShowVO;
import com.fuzamei.demo.service.TransferWaterService;
import com.fuzamei.demo.utils.ExportExcelUtils;
import com.fuzamei.demo.utils.ResponseData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/*
 * spring整合Junit4单元测试基类，
 * 其他类实现该类可以省略一些注解配置。
 * */
//使用junit4进行单元测试
@RunWith(SpringJUnit4ClassRunner.class)
//加载配置文件，可以指定多个配置文件，locations指定的是一个数组
@ContextConfiguration(locations={"classpath:spring/spring*.xml","classpath:spring/spring-mvc.xml"})
//启动事务控制
@Transactional
//配置事务管理器，同时指定自动回滚,如果是true,不管成功与否全部回滚(默认是true)
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class BaseJunit4Test extends TransferWaterController {
	//进行测试时，将测试类继承该类
    //注入service对象
    //然后在方法上使用@Test，@RollBack，@Transaction等注解单独修饰
    @Resource(name = "transferWaterServiceImpl")
    private TransferWaterService transferWaterService;

    @Test
    public void exportExcelAll(HttpServletRequest request, HttpServletResponse response){
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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
