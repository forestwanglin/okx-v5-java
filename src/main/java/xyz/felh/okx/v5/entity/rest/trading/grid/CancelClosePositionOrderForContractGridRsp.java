package xyz.felh.okx.v5.entity.rest.trading.grid;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import xyz.felh.okx.v5.entity.rest.IOkxRestRsp;

/**
 * @author Forest Wang
 * @package xyz.felh.okx.v5.entity.rest.trading.grid
 * @class CancelClosePositionOrderForContractGridReq
 * @email forestwanglin@gmail.cn
 * @date 2024/7/25
 */
@ToString
@Data
public class CancelClosePositionOrderForContractGridRsp implements IOkxRestRsp {

    /**
     * 策略订单ID
     */
    @JSONField(name = "algoId")
    @JsonProperty("algoId")
    private String algoId;

    /**
     * 平仓单ID
     */
    @JSONField(name = "ordId")
    @JsonProperty("ordId")
    private String ordId;

    /**
     * 用户自定义策略ID
     */
    @JSONField(name = "algoClOrdId")
    @JsonProperty("algoClOrdId")
    private String algoClOrdId;

    /**
     * 订单标签
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;

}