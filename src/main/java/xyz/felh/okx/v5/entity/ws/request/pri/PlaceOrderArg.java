package xyz.felh.okx.v5.entity.ws.request.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;
import xyz.felh.okx.v5.enumeration.OrderType;
import xyz.felh.okx.v5.enumeration.PositionsSide;
import xyz.felh.okx.v5.enumeration.Side;
import xyz.felh.okx.v5.enumeration.StpMode;
import xyz.felh.okx.v5.enumeration.ws.TdMode;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PlaceOrderArg implements WsRequestArg {

    /**
     * Instrument ID, e.g. BTC-USDT
     */
    @NonNull
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * Trade mode
     * Margin mode isolated cross
     * Non-Margin mode cash
     * spot_isolated (only applicable to SPOT lead trading, tdMode should be spot_isolated for SPOT lead trading.)
     */
    @NonNull
    @JSONField(name = "tdMode")
    @JsonProperty("tdMode")
    private TdMode tdMode;

    /**
     * Margin currency
     * Only applicable to cross MARGIN orders in Single-currency margin.
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * Client Order ID as assigned by the client
     * A combination of case-sensitive alphanumerics, all numbers, or all letters of up to 32 characters.
     */
    @JSONField(name = "clOrdId")
    @JsonProperty("clOrdId")
    private String clOrdId;

    /**
     * Order tag
     * A combination of case-sensitive alphanumerics, all numbers, or all letters of up to 16 characters.
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;

    /**
     * Order side, buy sell
     */
    @NonNull
    @JSONField(name = "side")
    @JsonProperty("side")
    private Side side;

    /**
     * Position side
     * The default is net in the net mode
     * It is required in the long/short mode, and can only be long or short.
     * Only applicable to FUTURES/SWAP.
     */
    @JSONField(name = "posSide")
    @JsonProperty("posSide")
    private PositionsSide posSide;

    /**
     * Order type
     * market: market order
     * limit: limit order
     * post_only: Post-only order
     * fok: Fill-or-kill order
     * ioc: Immediate-or-cancel order
     * optimal_limit_ioc: Market order with immediate-or-cancel order
     * mmp: Market Maker Protection (only applicable to Option in Portfolio Margin mode)
     * mmp_and_post_only: Market Maker Protection and Post-only order(only applicable to Option in Portfolio Margin mode)
     */
    @NonNull
    @JSONField(name = "ordType")
    @JsonProperty("ordType")
    private OrderType ordType;

    /**
     * Quantity to buy or sell.
     */
    @NonNull
    @JSONField(name = "sz")
    @JsonProperty("sz")
    private BigDecimal sz;

    /**
     * Order price. Only applicable to limit,post_only,fok,ioc,mmp,mmp_and_post_only order.
     * When placing an option order, one of px/pxUsd/pxVol must be filled in, and only one can be filled in
     */
    @JSONField(name = "px")
    @JsonProperty("px")
    private BigDecimal px;

    /**
     * Place options orders in USD
     * Only applicable to options
     * When placing an option order, one of px/pxUsd/pxVol must be filled in, and only one can be filled in
     */
    @JSONField(name = "pxUsd")
    @JsonProperty("pxUsd")
    private BigDecimal pxUsd;

    /**
     * Place options orders based on implied volatility, where 1 represents 100%
     * Only applicable to options
     * When placing an option order, one of px/pxUsd/pxVol must be filled in, and only one can be filled in
     */
    @JSONField(name = "pxVol")
    @JsonProperty("pxVol")
    private BigDecimal pxVol;

    /**
     * Whether the order can only reduce the position size.
     * Valid options: true or false. The default value is false.
     * Only applicable to MARGIN orders, and FUTURES/SWAP orders in net mode
     * Only applicable to Single-currency margin and Multi-currency margin
     */
    @JSONField(name = "reduceOnly")
    @JsonProperty("reduceOnly")
    private Boolean reduceOnly;

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
     * Whether to disallow the system from amending the size of the SPOT Market Order.
     * Valid options: true or false. The default value is false.
     * If true, system will not amend and reject the market order if user does not have sufficient funds.
     * Only applicable to SPOT Market Orders
     */
    @JSONField(name = "banAmend")
    @JsonProperty("banAmend")
    private Boolean banAmend;

    /**
     * Self trade prevention mode.
     * Default to cancel maker
     * cancel_maker,cancel_taker, cancel_both
     * Cancel both does not support FOK.
     */
    @JSONField(name = "stpMode")
    @JsonProperty("stpMode")
    private StpMode stpMode;

}
