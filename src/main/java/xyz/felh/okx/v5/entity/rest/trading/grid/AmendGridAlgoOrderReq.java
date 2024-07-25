package xyz.felh.okx.v5.entity.rest.trading.grid;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.rest.IOkxRestReq;
import xyz.felh.okx.v5.enumeration.TriggerAction;
import xyz.felh.okx.v5.enumeration.TriggerStrategy;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Forest Wang
 * @package xyz.felh.okx.v5.entity.rest.trading.grid
 * @class AmendGridAlgoOrderReq
 * @email forestwanglin@gmail.cn
 * @date 2024/7/25
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmendGridAlgoOrderReq implements IOkxRestReq {

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
     * 止盈触发价
     * 适用于现货网格/合约网格
     */
    @JSONField(name = "tpTriggerPx")
    @JsonProperty("tpTriggerPx")
    private BigDecimal tpTriggerPx;

    /**
     * 止损触发价
     * 适用于现货网格/合约网格
     */
    @JSONField(name = "slTriggerPx")
    @JsonProperty("slTriggerPx")
    private BigDecimal slTriggerPx;

    /**
     * 止盈比率，0.1 代表 10%，仅适用于合约网格
     * 当值为""则代表取消止盈比率
     */
    @JSONField(name = "tpRatio")
    @JsonProperty("tpRatio")
    private BigDecimal tpRatio;

    /**
     * 止损比率，0.1 代表 10%，仅适用于合约网格
     * 当值为""则代表取消止损比率
     */
    @JSONField(name = "slRatio")
    @JsonProperty("slRatio")
    private BigDecimal slRatio;

    /**
     * 信号触发参数
     * 适用于现货网格/合约网格
     */
    @JSONField(name = "triggerParams")
    @JsonProperty("triggerParams")
    private List<TriggerParam> triggerParams;

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TriggerParam {

        /**
         * Trigger action
         * start
         * stop
         */
        @JSONField(name = "triggerAction")
        @JsonProperty("triggerAction")
        private TriggerAction triggerAction;

        /**
         * Trigger strategy
         */
        @JSONField(name = "triggerStrategy")
        @JsonProperty("triggerStrategy")
        private TriggerStrategy triggerStrategy;

        /**
         * Trigger Price
         * This field is only valid when triggerStrategy is price
         */
        @JSONField(name = "triggerPx")
        @JsonProperty("triggerPx")
        private BigDecimal triggerPx;

        /**
         * Stop type
         * Spot grid 1: Sell base currency 2: Keep base currency
         * Contract grid 1: Market Close All positions 2: Keep positions
         * This field is only valid when triggerAction is stop
         */
        @JSONField(name = "stopType")
        @JsonProperty("stopType")
        private Integer stopType;

    }

}