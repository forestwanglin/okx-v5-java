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
import xyz.felh.okx.v5.enumeration.ws.AlgoOrderType;
import xyz.felh.okx.v5.enumeration.ws.TdMode;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class GridSubOrder implements WsSubscribeEntity {

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
     * Instrument ID
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * Algo order type
     */
    @JSONField(name = "algoOrdType")
    @JsonProperty("algoOrdType")
    private AlgoOrderType algoOrdType;

    /**
     * Group ID
     */
    @JSONField(name = "groupId")
    @JsonProperty("groupId")
    private String groupId;

    /**
     * Sub order ID
     */
    @JSONField(name = "ordId")
    @JsonProperty("ordId")
    private String ordId;

    /**
     * Sub order created time, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "cTime")
    @JsonProperty("cTime")
    private Long cTime;

    /**
     * Sub order updated time, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "uTime")
    @JsonProperty("uTime")
    private Long uTime;

    /**
     * Sub order trade mode
     * Margin mode cross isolated
     * Non-Margin mode cash
     */
    @JSONField(name = "tdMode")
    @JsonProperty("tdMode")
    private TdMode tdMode;

    /**
     * 如Order tag
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;

    /**
     * Sub order type
     * market: Market order
     * limit: Limit order
     * ioc: Immediate-or-cancel order
     */
    @JSONField(name = "ordType")
    @JsonProperty("ordType")
    private OrderType ordType;

    /**
     * Sub order quantity to buy or sell
     */
    @JSONField(name = "sz")
    @JsonProperty("sz")
    private BigDecimal sz;

    /**
     * Sub order state
     * canceled
     * live
     * partially_filled
     * filled
     * cancelling
     */
    @JSONField(name = "state")
    @JsonProperty("state")
    private OrderState state;

    /**
     * Sub order side
     * buy sell
     */
    @JSONField(name = "side")
    @JsonProperty("side")
    private Side side;

    /**
     * Sub order price
     */
    @JSONField(name = "px")
    @JsonProperty("px")
    private BigDecimal px;

    /**
     * Sub order fee amount
     */
    @JSONField(name = "fee")
    @JsonProperty("fee")
    private BigDecimal fee;

    /**
     * Sub order fee currency
     */
    @JSONField(name = "feeCcy")
    @JsonProperty("feeCcy")
    private String feeCcy;

    /**
     * Sub order rebate amount
     */
    @JSONField(name = "rebate")
    @JsonProperty("rebate")
    private BigDecimal rebate;

    /**
     * Sub order rebate currency
     */
    @JSONField(name = "rebateCcy")
    @JsonProperty("rebateCcy")
    private String rebateCcy;

    /**
     * Sub order average filled price
     */
    @JSONField(name = "avgPx")
    @JsonProperty("avgPx")
    private BigDecimal avgPx;

    /**
     * Sub order accumulated fill quantity
     */
    @JSONField(name = "accFillSz")
    @JsonProperty("accFillSz")
    private BigDecimal accFillSz;

    /**
     * Sub order position side
     * net
     */
    @JSONField(name = "posSide")
    @JsonProperty("posSide")
    private PositionsSide posSide;

    /**
     * Sub order profit and loss
     */
    @JSONField(name = "pnl")
    @JsonProperty("pnl")
    private BigDecimal pnl;

    /**
     * Contract value
     * Only applicable to FUTURES/SWAP/OPTION
     */
    @JSONField(name = "ctVal")
    @JsonProperty("ctVal")
    private String ctVal;

    /**
     * Leverage
     */
    @JSONField(name = "lever")
    @JsonProperty("lever")
    private String lever;

    /**
     * Push time of orders information, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "pTime")
    @JsonProperty("pTime")
    private Long pTime;

}
