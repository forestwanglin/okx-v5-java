package xyz.felh.okx.v5.entity.rest.trading.grid;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.rest.IOkxRestReq;
import xyz.felh.okx.v5.enumeration.AlgoOrderType;

/**
 * @author Forest Wang
 * @package xyz.felh.okx.v5.entity.rest.trading.grid
 * @class StopGridAlgoOrderReq
 * @email forestwanglin@gmail.cn
 * @date 2024/7/25
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StopGridAlgoOrderReq implements IOkxRestReq {

    /**
     * 策略订单ID
     */
    @NonNull
    @JSONField(name = "algoId")
    @JsonProperty("algoId")
    private String algoId;

    /**
     * 产品ID，如BTC-USDT
     */
    @NonNull
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * 策略订单类型
     * grid：现货网格委托
     * contract_grid：合约网格委托
     */
    @NonNull
    @JSONField(name = "algoOrdType")
    @JsonProperty("algoOrdType")
    private AlgoOrderType algoOrdType;

    /**
     * 网格策略停止类型
     * 现货网格 1：卖出交易币，2：不卖出交易币
     * 合约网格 1：市价全平 2：停止不平仓
     */
    @NonNull
    @JSONField(name = "stopType")
    @JsonProperty("stopType")
    private String stopType;

}