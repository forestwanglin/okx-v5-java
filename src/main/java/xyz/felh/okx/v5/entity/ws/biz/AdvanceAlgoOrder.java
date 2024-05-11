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
import xyz.felh.okx.v5.enumeration.OrderType;
import xyz.felh.okx.v5.enumeration.PositionsSide;
import xyz.felh.okx.v5.enumeration.Side;
import xyz.felh.okx.v5.enumeration.ws.InstrumentType;
import xyz.felh.okx.v5.enumeration.ws.TdMode;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AdvanceAlgoOrder implements WsSubscribeEntity {

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
     * Only applicable to cross MARGIN orders in Single-currency margin.
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * Order ID, the order ID associated with the algo order.
     */
    @JSONField(name = "ordId")
    @JsonProperty("ordId")
    private String ordId;

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
     * Quantity to buy or sell. SPOT/MARGIN: in the unit of currency. FUTURES/SWAP/OPTION: in the unit of contract.
     */
    @JSONField(name = "sz")
    @JsonProperty("sz")
    private BigDecimal sz;

    /**
     * Order type
     * iceberg: Iceberg order
     * twap: TWAP order
     * move_order_stop: Trailing order
     */
    @JSONField(name = "ordType")
    @JsonProperty("ordType")
    private OrderType ordType;

    /**
     * Order side, buy sell
     */
    @JSONField(name = "side")
    @JsonProperty("side")
    private Side side;

    /**
     * Position side
     * net
     * long or short Only applicable to FUTURES/SWAP
     */
    @JSONField(name = "posSide")
    @JsonProperty("posSide")
    private PositionsSide posSide;

    /**
     * Trade mode, cross: cross isolated: isolated cash: cash
     */
    @JSONField(name = "tdMode")
    @JsonProperty("tdMode")
    private TdMode tdMode;

    /**
     * Order quantity unit setting for sz
     * base_ccy: Base currency ,quote_ccy: Quote currency
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
     * partially_effective: partially effective
     * canceled: canceled
     * order_failed: order failed
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
     * Order price
     */
    @JSONField(name = "ordPx")
    @JsonProperty("ordPx")
    private BigDecimal ordPx;

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
     * Creation time, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "cTime")
    @JsonProperty("cTime")
    private Long cTime;

    /**
     * Price ratio
     * Only applicable to iceberg order or twap order
     */
    @JSONField(name = "pxVar")
    @JsonProperty("pxVar")
    private BigDecimal pxVar;

    /**
     * Price variance
     * Only applicable to iceberg order or twap order
     */
    @JSONField(name = "pxSpread")
    @JsonProperty("pxSpread")
    private BigDecimal pxSpread;

    /**
     * Average amount
     * Only applicable to iceberg order or twap order
     */
    @JSONField(name = "szLimit")
    @JsonProperty("szLimit")
    private BigDecimal szLimit;

    /**
     * Price limit
     * Only applicable to iceberg order or twap order
     */
    @JSONField(name = "pxLimit")
    @JsonProperty("pxLimit")
    private BigDecimal pxLimit;

    /**
     * Time interval
     * Only applicable to twap order
     */
    @JSONField(name = "timeInterval")
    @JsonProperty("timeInterval")
    private String timeInterval;

    /**
     * Algo Order count
     * Only applicable to iceberg order or twap order
     */
    @JSONField(name = "count")
    @JsonProperty("count")
    private Integer count;

    /**
     * Callback price ratio
     * Only applicable to move_order_stop order
     */
    @JSONField(name = "callbackRatio")
    @JsonProperty("callbackRatio")
    private BigDecimal callbackRatio;

    /**
     * Callback price variance
     * Only applicable to move_order_stop order
     */
    @JSONField(name = "callbackSpread")
    @JsonProperty("callbackSpread")
    private BigDecimal callbackSpread;

    /**
     * Active price
     * Only applicable to move_order_stop order
     */
    @JSONField(name = "activePx")
    @JsonProperty("activePx")
    private BigDecimal activePx;

    /**
     * Trigger price
     * Only applicable to move_order_stop order
     */
    @JSONField(name = "moveTriggerPx")
    @JsonProperty("moveTriggerPx")
    private BigDecimal moveTriggerPx;

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
     * Whether the order can only reduce the position size. Valid options: true or false.
     */
    @JSONField(name = "reduceOnly")
    @JsonProperty("reduceOnly")
    private Boolean reduceOnly;

    /**
     * Push time of algo order information, millisecond format of Unix timestamp, e.g. 1597026383085
     */
    @JSONField(name = "pTime")
    @JsonProperty("pTime")
    private Long pTime;


}
