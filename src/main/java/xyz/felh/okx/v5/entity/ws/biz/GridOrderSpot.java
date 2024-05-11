package xyz.felh.okx.v5.entity.ws.biz;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.enumeration.OrderState;
import xyz.felh.okx.v5.enumeration.ws.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class GridOrderSpot implements WsSubscribeEntity {

    /**
     * Algo ID
     */
    @JSONField(name = "algoId")
    @JsonProperty("algoId")
    private String algoId;

    /**
     * Client-supplied Algo ID
     */
    @JSONField(name = "algoClOrdId")
    @JsonProperty("algoClOrdId")
    private String algoClOrdId;

    /**
     * Instrument type
     */
    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

    /**
     * Instrument ID
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * Algo order created time, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "cTime")
    @JsonProperty("cTime")
    private Long cTime;

    /**
     * Algo order updated time, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "uTime")
    @JsonProperty("uTime")
    private Long uTime;

    /**
     * Algo order triggered time, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "triggerTime")
    @JsonProperty("triggerTime")
    private Long triggerTime;

    /**
     * 币Algo order type
     * grid: Spot grid
     */
    @JSONField(name = "algoOrdType")
    @JsonProperty("algoOrdType")
    private AlgoOrderType algoOrdType;

    /**
     * Algo order state
     * starting
     * running
     * stopping
     * stopped
     */
    @JSONField(name = "state")
    @JsonProperty("state")
    private OrderState state;

    /**
     * Rebate transfer info
     */
    @JSONField(name = "rebateTrans")
    @JsonProperty("rebateTrans")
    private List<RebateTran> rebateTrans;

    /**
     * Trigger Parameters
     */
    @JSONField(name = "triggerParams")
    @JsonProperty("triggerParams")
    private List<TriggerParam> triggerParams;

    /**
     * Upper price of price range
     */
    @JSONField(name = "maxPx")
    @JsonProperty("maxPx")
    private BigDecimal maxPx;

    /**
     * Lower price of price range
     */
    @JSONField(name = "minPx")
    @JsonProperty("minPx")
    private BigDecimal minPx;

    /**
     * Grid quantity
     */
    @JSONField(name = "gridNum")
    @JsonProperty("gridNum")
    private BigDecimal gridNum;

    /**
     * Grid type
     * 1: Arithmetic, 2: Geometric
     */
    @JSONField(name = "runType")
    @JsonProperty("runType")
    private Integer runType;

    /**
     * Take-profit trigger price
     */
    @JSONField(name = "tpTriggerPx")
    @JsonProperty("tpTriggerPx")
    private BigDecimal tpTriggerPx;

    /**
     * Stop-loss trigger price
     */
    @JSONField(name = "slTriggerPx")
    @JsonProperty("slTriggerPx")
    private BigDecimal slTriggerPx;

    /**
     * The number of trades executed
     */
    @JSONField(name = "tradeNum")
    @JsonProperty("tradeNum")
    private Integer tradeNum;

    /**
     * The number of arbitrages executed
     */
    @JSONField(name = "arbitrageNum")
    @JsonProperty("arbitrageNum")
    private Integer arbitrageNum;

    /**
     * Amount per grid
     */
    @JSONField(name = "singleAmt")
    @JsonProperty("singleAmt")
    private BigDecimal singleAmt;

    /**
     * Estimated minimum Profit margin per grid
     */
    @JSONField(name = "perMinProfitRate")
    @JsonProperty("perMinProfitRate")
    private BigDecimal perMinProfitRate;

    /**
     * Estimated maximum Profit margin per grid
     */
    @JSONField(name = "perMaxProfitRate")
    @JsonProperty("perMaxProfitRate")
    private BigDecimal perMaxProfitRate;

    /**
     * Price at launch
     */
    @JSONField(name = "runPx")
    @JsonProperty("runPx")
    private BigDecimal runPx;

    /**
     * Total P&L
     */
    @JSONField(name = "totalPnl")
    @JsonProperty("totalPnl")
    private BigDecimal totalPnl;

    /**
     * P&L ratio
     */
    @JSONField(name = "pnlRatio")
    @JsonProperty("pnlRatio")
    private BigDecimal pnlRatio;

    /**
     * Investment amount
     * Spot grid investment amount calculated on quote currency
     */
    @JSONField(name = "investment")
    @JsonProperty("investment")
    private BigDecimal investment;

    /**
     * Grid profit
     */
    @JSONField(name = "gridProfit")
    @JsonProperty("gridProfit")
    private BigDecimal gridProfit;

    /**
     * Variable P&L
     */
    @JSONField(name = "floatProfit")
    @JsonProperty("floatProfit")
    private BigDecimal floatProfit;

    /**
     * Total annualized rate
     */
    @JSONField(name = "totalAnnualizedRate")
    @JsonProperty("totalAnnualizedRate")
    private BigDecimal totalAnnualizedRate;

    /**
     * Grid annualized rate
     */
    @JSONField(name = "annualizedRate")
    @JsonProperty("annualizedRate")
    private BigDecimal annualizedRate;

    /**
     * Algo order stop reason
     * 0: None
     * 1: Manual stop
     * 2: Take profit
     * 3: Stop loss
     * 4: Risk control
     * 5: Delivery
     * 6: Signal
     */
    @JSONField(name = "cancelType")
    @JsonProperty("cancelType")
    private Integer cancelType;

    /**
     * Stop type
     * 1: Sell base currency 2: Keep base currency
     */
    @JSONField(name = "stopType")
    @JsonProperty("stopType")
    private Integer stopType;

    /**
     * Quote currency investment amount
     * Only applicable to Spot grid
     */
    @JSONField(name = "quoteSz")
    @JsonProperty("quoteSz")
    private BigDecimal quoteSz;

    /**
     * Base currency investment amount
     * Only applicable to Spot grid
     */
    @JSONField(name = "baseSz")
    @JsonProperty("baseSz")
    private BigDecimal baseSz;

    /**
     * Assets of quote currency currently held
     * Only applicable to Spot grid
     */
    @JSONField(name = "curQuoteSz")
    @JsonProperty("curQuoteSz")
    private BigDecimal curQuoteSz;

    /**
     * Assets of base currency currently held
     * Only applicable to Spot grid
     */
    @JSONField(name = "curBaseSz")
    @JsonProperty("curBaseSz")
    private BigDecimal curBaseSz;

    /**
     * Current available profit based on quote currency
     * Only applicable to Spot grid
     */
    @JSONField(name = "profit")
    @JsonProperty("profit")
    private BigDecimal profit;

    /**
     * Stop result
     * 0: default, 1: Successful selling of currency at market price, -1: Failed to sell currency at market price
     * Only applicable to Spot grid
     */
    @JSONField(name = "stopResult")
    @JsonProperty("stopResult")
    private Integer stopResult;

    /**
     * Total count of pending sub orders
     */
    @JSONField(name = "activeOrdNum")
    @JsonProperty("activeOrdNum")
    private Integer activeOrdNum;

    /**
     * Order tag
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;

    /**
     * Profit sharing ratio
     * Value range [0, 0.3]
     * If it is a normal order (neither copy order nor lead order), this field returns ""
     */
    @JSONField(name = "profitSharingRatio")
    @JsonProperty("profitSharingRatio")
    private BigDecimal profitSharingRatio;

    /**
     * Profit sharing order type
     * 0: Normal order
     * 1: Copy order without profit sharing
     * 2: Copy order with profit sharing
     * 3: Lead order
     */
    @JSONField(name = "copyType")
    @JsonProperty("copyType")
    private Integer copyType;

    /**
     * Push time of algo grid information, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "pTime")
    @JsonProperty("pTime")
    private Long pTime;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class RebateTran {

        /**
         * Rebate amount
         */
        @JSONField(name = "rebate")
        @JsonProperty("rebate")
        private BigDecimal rebate;

        /**
         * Rebate currency
         */
        @JSONField(name = "rebateCcy")
        @JsonProperty("rebateCcy")
        private String rebateCcy;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
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
