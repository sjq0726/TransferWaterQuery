package com.fuzamei.demo.model.VO;

import com.fuzamei.demo.model.TransferWater;
import com.github.pagehelper.Page;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class TransferWaterResponseVO {
    private Page<TransferWater> transferWaterPage;
    private String message;
}
