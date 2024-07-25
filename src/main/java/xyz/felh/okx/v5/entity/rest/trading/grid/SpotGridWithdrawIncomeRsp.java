package xyz.felh.okx.v5.entity.rest.trading.grid;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import xyz.felh.okx.v5.entity.rest.IOkxRestRsp;

import java.math.BigDecimal;

/**
 * @author Forest Wang
 * @package xyz.felh.okx.v5.entity.rest.trading.grid
 * @class SpotGridWithdrawIncomeRsp
 * @email forestwanglin@gmail.cn
 * @date 2024/7/25
 */
@ToString
@Data
public class SpotGridWithdrawIncomeRsp implements IOkxRestRsp {

    /**
     * 策略订单ID
     */
    @JSONField(name = "algoId")
    @JsonProperty("algoId")
    private String algoId;

    /**
     * 用户自定义策略ID
     */
    @JSONField(name = "algoClOrdId")
    @JsonProperty("algoClOrdId")
    private String algoClOrdId;

    /**
     * 提取的利润
     */
    @JSONField(name = "profit")
    @JsonProperty("profit")
    private BigDecimal profit;

}