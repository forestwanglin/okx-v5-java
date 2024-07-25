package xyz.felh.okx.v5.entity.rest.trading.grid;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import xyz.felh.okx.v5.entity.rest.IOkxRestRsp;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Forest Wang
 * @package xyz.felh.okx.v5.entity.rest.trading.grid
 * @class ComputeGridMinInvestmentReq
 * @email forestwanglin@gmail.cn
 * @date 2024/7/25
 */
@ToString
@Data
public class ComputeGridMinInvestmentRsp implements IOkxRestRsp {

    /**
     * 最小投入信息
     */
    @JSONField(name = "minInvestmentData")
    @JsonProperty("minInvestmentData")
    private List<ComputeGridMinInvestmentReq.InvestmentData> minInvestmentData;

    /**
     * 单网格买卖量
     * 现货网格单位为计价币
     * 合约网格单位为张
     */
    @JSONField(name = "singleAmt")
    @JsonProperty("singleAmt")
    private BigDecimal singleAmt;

}