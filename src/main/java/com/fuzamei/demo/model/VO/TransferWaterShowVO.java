package com.fuzamei.demo.model.VO;

import lombok.Data;

@Data
public class TransferWaterShowVO {

    private String ACCName;//对方账号名称

    private String AMTIn;//转入

    private String AMTOut;//转出

    private String total;//转入-转出
}
