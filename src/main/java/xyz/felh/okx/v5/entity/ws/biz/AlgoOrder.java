package xyz.felh.okx.v5.entity.ws.biz;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.enumeration.*;
import xyz.felh.okx.v5.enumeration.InstrumentType;
import xyz.felh.okx.v5.enumeration.ws.TdMode;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AlgoOrder implements WsSubscribeEntity {

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
     * Margin currency
     * Only applicable to cross M
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * Latest order ID, the order ID associated with the algo order.
     */
    @JSONField(name = "ordId")
    @JsonProperty("ordId")
    private String ordId;

    /**
     * Order ID list. There will be multiple order IDs when there is TP/SL splitting order.
     */
    @JSONField(name = "ordIdList")
    @JsonProperty("ordIdList")
    private List<String> ordIdList;

    /**
     * Algo ID
     */
    @JSONField(name = "algoId")
    @JsonProperty("algoId")
    private String algoId;

    /**
     * Client Order ID as assigned by the client
     */
    @JSONField(name = "clOrdId")
    @JsonProperty("clOrdId")
    private String clOrdId;

    /**
     * Quantity to buy or sell.
     * SPOT/MARGIN: in the unit of currency.
     * FUTURES/SWAP/OPTION: in the unit of contract.
     */
    @JSONField(name = "sz")
    @JsonProperty("sz")
    private BigDecimal sz;

    /**
     * Order type
     * conditional: One-way stop order
     * oco: One-cancels-the-other order
     * trigger: Trigger order
     */
    @JSONField(name = "ordType")
    @JsonProperty("ordType")
    private OrderType ordType;

    /**
     * Order side
     * buy
     * sell
     */
    @JSONField(name = "side")
    @JsonProperty("side")
    private Side side;

    /**
     * Position side
     * net
     * long or short
     * Only applicable to FUTURES/SWAP
     */
    @JSONField(name = "posSide")
    @JsonProperty("posSide")
    private PositionsSide posSide;

    /**
     * Trade mode
     * cross: cross
     * isolated: isolated
     * cash: cash
     */
    @JSONField(name = "tdMode")
    @JsonProperty("tdMode")
    private TdMode tdMode;

    /**
     * Order quantity unit setting for sz
     * base_ccy: Base currency
     * quote_ccy: Quote currency
     * Only applicable to SPOT Market Orders
     * Default is quote_ccy for buy, base_ccy for sell
     */
    @JSONField(name = "tgtCcy")
    @JsonProperty("tgtCcy")
    private String tgtCcy;

    /**
     * Leverage, from 0.01 to 125.
     * Only applicable to MARGIN/FUTURES/SWAP
     */
    @JSONField(name = "lever")
    @JsonProperty("lever")
    private Double lever;

    /**
     * Order status
     * live: to be effective
     * effective: effective
     * canceled: canceled
     * order_failed: order failed
     * partially_failed: partially failed
     */
    @JSONField(name = "state")
    @JsonProperty("state")
    private OrderState state;

    /**
     * Take-profit trigger price.
     */
    @JSONField(name = "tpTriggerPx")
    @JsonProperty("tpTriggerPx")
    private BigDecimal tpTriggerPx;

    /**
     * Take-profit trigger price type.
     * last: last price
     * index: index price
     * mark: mark price
     */
    @JSONField(name = "tpTriggerPxType")
    @JsonProperty("tpTriggerPxType")
    private TriggerPxType tpTriggerPxType;

    /**
     * Take-profit order price.
     */
    @JSONField(name = "tpOrdPx")
    @JsonProperty("tpOrdPx")
    private BigDecimal tpOrdPx;

    /**
     * Stop-loss trigger price.
     */
    @JSONField(name = "slTriggerPx")
    @JsonProperty("slTriggerPx")
    private BigDecimal slTriggerPx;

    /**
     * Stop-loss trigger price type.
     * last: last price
     * index: index price
     * mark: mark price
     */
    @JSONField(name = "slTriggerPxType")
    @JsonProperty("slTriggerPxType")
    private TriggerPxType slTriggerPxType;

    /**
     * Stop-loss order price.
     */
    @JSONField(name = "slOrdPx")
    @JsonProperty("slOrdPx")
    private BigDecimal slOrdPx;

    /**
     * Trigger price
     */
    @JSONField(name = "triggerPx")
    @JsonProperty("triggerPx")
    private BigDecimal triggerPx;

    /**
     * Trigger price type.
     * last: last price
     * index: index price
     * mark: mark price
     */
    @JSONField(name = "triggerPxType")
    @JsonProperty("triggerPxType")
    private TriggerPxType triggerPxType;

    /**
     * Order price
     */
    @JSONField(name = "ordPx")
    @JsonProperty("ordPx")
    private BigDecimal ordPx;

    /**
     * Last filled price while placing
     */
    @JSONField(name = "last")
    @JsonProperty("last")
    private BigDecimal last;

    /**
     * Actual order quantity
     */
    @JSONField(name = "actualSz")
    @JsonProperty("actualSz")
    private BigDecimal actualSz;

    /**
     * Actual order price
     */
    @JSONField(name = "actualPx")
    @JsonProperty("actualPx")
    private BigDecimal actualPx;

    /**
     * Estimated national value in USD of order
     */
    @JSONField(name = "notionalUsd")
    @JsonProperty("notionalUsd")
    private BigDecimal notionalUsd;

    /**
     * Order tag
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;

    /**
     * Actual trigger side
     * Only applicable to oco order and conditional order
     */
    @JSONField(name = "actualSide")
    @JsonProperty("actualSide")
    private String actualSide;

    /**
     * Trigger time, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "triggerTime")
    @JsonProperty("triggerTime")
    private Long triggerTime;

    /**
     * Whether the order can only reduce the position size. Valid options: true or false.
     */
    @JSONField(name = "reduceOnly")
    @JsonProperty("reduceOnly")
    private Boolean reduceOnly;

    /**
     * It represents that the reason that algo order fails to trigger. It is "" when the state is effective/canceled. There will be value when the state is order_failed, e.g. 51008;
     * Only applicable to Stop Order, Trailing Stop Order, Trigger order.
     */
    @JSONField(name = "failCode")
    @JsonProperty("failCode")
    private String failCode;

    /**
     * Client Algo Order ID as assigned by the client.
     */
    @JSONField(name = "algoClOrdId")
    @JsonProperty("algoClOrdId")
    private String algoClOrdId;

    /**
     * Client Request ID as assigned by the client for order amendment. "" will be returned if there is no order amendment.
     */
    @JSONField(name = "reqId")
    @JsonProperty("reqId")
    private String reqId;

    /**
     * The result of amending the order
     * -1: failure
     * 0: success
     */
    @JSONField(name = "amendResult")
    @JsonProperty("amendResult")
    private Integer amendResult;

    /**
     * Whether to enable Cost-price SL. Only applicable to SL order of split TPs.
     * 0: disable, the default value
     * 1: Enable
     */
    @JSONField(name = "amendPxOnTriggerType")
    @JsonProperty("amendPxOnTriggerType")
    private Integer amendPxOnTriggerType;

    /**
     * Creation time Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "cTime")
    @JsonProperty("cTime")
    private Long cTime;

    /**
     * Order updated time, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "uTime")
    @JsonProperty("uTime")
    private Long uTime;

    /**
     * Attached SL/TP orders info
     * Applicable to Single-currency margin/Multi-currency margin/Portfolio margin
     */
    @JSONField(name = "attachAlgoOrds")
    @JsonProperty("attachAlgoOrds")
    private List<AttachAloOrder> attachAlgoOrds;

    /**
     * Linked TP order detail, only applicable to TP limit order of one-cancels-the-other order(oco)
     */
    @JSONField(name = "linkedOrd")
    @JsonProperty("linkedOrd")
    private LinkedOrder linkedOrd;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LinkedOrder {
        /**
         * 策略订单唯一标识
         */
        @JSONField(name = "algoId")
        @JsonProperty("algoId")
        private String algoId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AttachAloOrder {

        /**
         * 下单附带止盈止损时，客户自定义的策略订单ID
         */
        @JSONField(name = "attachAlgoClOrdId")
        @JsonProperty("attachAlgoClOrdId")
        private String attachAlgoClOrdId;

        /**
         * 止盈触发价
         */
        @JSONField(name = "tpTriggerPx")
        @JsonProperty("tpTriggerPx")
        private BigDecimal tpTriggerPx;

        /**
         * 止盈触发价类型
         */
        @JSONField(name = "tpTriggerPxType")
        @JsonProperty("tpTriggerPxType")
        private TriggerPxType tpTriggerPxType;

        /**
         * 止盈委托价
         */
        @JSONField(name = "tpOrdPx")
        @JsonProperty("tpOrdPx")
        private BigDecimal tpOrdPx;

        /**
         * 止损触发价
         */
        @JSONField(name = "slTriggerPx")
        @JsonProperty("slTriggerPx")
        private BigDecimal slTriggerPx;

        /**
         * 止损触发价类型
         */
        @JSONField(name = "slTriggerPxType")
        @JsonProperty("slTriggerPxType")
        private TriggerPxType slTriggerPxType;

        /**
         * 止损委托价
         */
        @JSONField(name = "slOrdPx")
        @JsonProperty("slOrdPx")
        private BigDecimal slOrdPx;
    }

}
