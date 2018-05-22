package com.fuzamei.demo.model.VO;

import com.fuzamei.demo.model.NewTransferWater;
import com.github.pagehelper.Page;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class TransferWaterResponseVO {
    private Page<NewTransferWater> transferWaterPage;
   
}
