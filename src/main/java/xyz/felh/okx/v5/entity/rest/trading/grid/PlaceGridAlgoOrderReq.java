package xyz.felh.okx.v5.entity.rest.trading.grid;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.rest.IOkxRestReq;
import xyz.felh.okx.v5.enumeration.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Forest Wang
 * @package xyz.felh.okx.v5.entity.rest.trading.grid
 * @class PlaceGridAlgoOrderReq
 * @email forestwanglin@gmail.cn
 * @date 2024/7/25
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceGridAlgoOrderReq implements IOkxRestReq {

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
     * 网格数量
     */
    @NonNull
    @JSONField(name = "gridNum")
    @JsonProperty("gridNum")
    private Integer gridNum;

    /**
     * 区间最高价格
     */
    @NonNull
    @JSONField(name = "maxPx")
    @JsonProperty("maxPx")
    private BigDecimal maxPx;

    /**
     * 区间最低价格
     */
    @NonNull
    @JSONField(name = "minPx")
    @JsonProperty("minPx")
    private BigDecimal minPx;

    /**
     * 网格类型
     * 1：等差，2：等比
     * 默认为等差
     */
    @JSONField(name = "runType")
    @JsonProperty("runType")
    private Integer runType;

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
     * 用户自定义策略ID
     * 字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。
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

    /**
     * 带单员分润比例，仅支持固定比例分润
     * 0,0.1,0.2,0.3
     */
    @JSONField(name = "profitSharingRatio")
    @JsonProperty("profitSharingRatio")
    private BigDecimal profitSharingRatio;

    // 现货网格
    /**
     * 计价币投入数量
     * quoteSz和baseSz至少指定一个
     */
    @JSONField(name = "quoteSz")
    @JsonProperty("quoteSz")
    private BigDecimal quoteSz;

    /**
     * 交易币投入数量
     * quoteSz和baseSz至少指定一个
     */
    @JSONField(name = "baseSz")
    @JsonProperty("baseSz")
    private BigDecimal baseSz;

    // 合约网格
    /**
     * 投入保证金,单位为USDT
     */
    @JSONField(name = "sz")
    @JsonProperty("sz")
    private BigDecimal sz;

    /**
     * 合约网格类型
     * long：做多，short：做空，neutral：中性
     */
    @JSONField(name = "direction")
    @JsonProperty("direction")
    private ContractDirection direction;

    /**
     * 杠杆倍数
     */
    @JSONField(name = "lever")
    @JsonProperty("lever")
    private Integer lever;

    /**
     * 是否开底仓
     * 默认为false
     * 中性合约网格忽略该参数
     */
    @JSONField(name = "basePos")
    @JsonProperty("basePos")
    private Boolean basePos;

    /**
     * 止盈比率，0.1 代表 10%
     */
    @JSONField(name = "tpRatio")
    @JsonProperty("tpRatio")
    private BigDecimal tpRatio;

    /**
     * 止损比率，0.1 代表 10%
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
         * Delay seconds after action triggered
         */
        @JSONField(name = "delaySeconds")
        @JsonProperty("delaySeconds")
        private Long delaySeconds;

        /**
         * Actual action triggered time, unix timestamp format in milliseconds, e.g. 1597026383085
         */
        @JSONField(name = "triggerTime")
        @JsonProperty("triggerTime")
        private Long triggerTime;

        /**
         * Actual action triggered type
         * manual
         * auto
         */
        @JSONField(name = "triggerType")
        @JsonProperty("triggerType")
        private TriggerType triggerType;

        /**
         * K-line type
         * 3m, 5m, 15m, 30m (m: minute)
         * 1H, 4H (H: hour)
         * 1D (D: day)
         * This field is only valid when triggerStrategy is rsi
         */
        @JSONField(name = "timeframe")
        @JsonProperty("timeframe")
        private String timeframe;

        /**
         * Threshold
         * The value should be an integer between 1 to 100
         * This field is only valid when triggerStrategy is rsi
         */
        @JSONField(name = "thold")
        @JsonProperty("thold")
        private Integer thold;

        /**
         * Trigger condition
         */
        @JSONField(name = "triggerCond")
        @JsonProperty("triggerCond")
        private TriggerCondition triggerCond;

        /**
         * Time Period
         * 14
         * This field is only valid when triggerStrategy is rsi
         */
        @JSONField(name = "timePeriod")
        @JsonProperty("timePeriod")
        private Integer timePeriod;

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