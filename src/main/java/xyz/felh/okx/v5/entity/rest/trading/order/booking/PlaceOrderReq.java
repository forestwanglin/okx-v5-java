package xyz.felh.okx.v5.entity.rest.trading.order.booking;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.rest.IOkxRestReq;
import xyz.felh.okx.v5.enumeration.OrderType;
import xyz.felh.okx.v5.enumeration.PositionsSide;
import xyz.felh.okx.v5.enumeration.Side;
import xyz.felh.okx.v5.enumeration.TriggerPxType;
import xyz.felh.okx.v5.enumeration.rest.StpMode;
import xyz.felh.okx.v5.enumeration.rest.TpOrderKind;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderReq implements IOkxRestReq {

    /**
     * 产品ID，如 BTC-USDT
     */
    @NonNull
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * 交易模式
     * 保证金模式：isolated：逐仓 ；cross：全仓
     * 非保证金模式：cash：非保证金
     * spot_isolated：现货逐仓(仅适用于现货带单) ，现货带单时，tdMode 的值需要指定为spot_isolated
     */
    @NonNull
    @JSONField(name = "tdMode")
    @JsonProperty("tdMode")
    private String tdMode;

    /**
     * 保证金币种，仅适用于单币种保证金模式下的全仓杠杆订单
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 客户自定义订单ID
     * 字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。
     */
    @JSONField(name = "clOrdId")
    @JsonProperty("clOrdId")
    private String clOrdId;

    /**
     * 订单标签
     * 字母（区分大小写）与数字的组合，可以是纯字母、纯数字，且长度在1-16位之间。
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;

    /**
     * 订单方向
     * buy：买， sell：卖
     */
    @NonNull
    @JSONField(name = "side")
    @JsonProperty("side")
    private Side side;

    /**
     * 持仓方向
     * 在开平仓模式下必填，且仅可选择 long 或 short。 仅适用交割、永续。
     */
    @JSONField(name = "posSide")
    @JsonProperty("posSide")
    private PositionsSide posSide;

    /**
     * 订单类型
     */
    @NonNull
    @JSONField(name = "ordType")
    @JsonProperty("ordType")
    private OrderType ordType;

    /**
     * 委托数量
     */
    @NonNull
    @JSONField(name = "sz")
    @JsonProperty("sz")
    private BigDecimal sz;

    /**
     * 委托价格，仅适用于limit、post_only、fok、ioc、mmp、mmp_and_post_only类型的订单
     * 期权下单时，px/pxUsd/pxVol 只能填一个
     */
    @JSONField(name = "px")
    @JsonProperty("px")
    private BigDecimal px;

    /**
     * 以USD价格进行期权下单
     * 仅适用于期权
     * 期权下单时 px/pxUsd/pxVol 必填一个，且只能填一个
     */
    @JSONField(name = "pxUsd")
    @JsonProperty("pxUsd")
    private BigDecimal pxUsd;

    /**
     * 以隐含波动率进行期权下单，例如 1 代表 100%
     * 仅适用于期权
     * 期权下单时 px/pxUsd/pxVol 必填一个，且只能填一个
     */
    @JSONField(name = "pxVol")
    @JsonProperty("pxVol")
    private BigDecimal pxVol;

    /**
     * 是否只减仓，true 或 false，默认false
     * 仅适用于币币杠杆，以及买卖模式下的交割/永续
     * 仅适用于单币种保证金模式和跨币种保证金模式
     */
    @JSONField(name = "reduceOnly")
    @JsonProperty("reduceOnly")
    private Boolean reduceOnly;

    /**
     * 市价单委托数量sz的单位，仅适用于币币市价订单
     * base_ccy: 交易货币 ；quote_ccy：计价货币
     * 买单默认quote_ccy， 卖单默认base_ccy
     */
    @JSONField(name = "tgtCcy")
    @JsonProperty("tgtCcy")
    private String tgtCcy;

    /**
     * 是否禁止币币市价改单，true 或 false，默认false
     * 为true时，余额不足时，系统不会改单，下单会失败，仅适用于币币市价单
     */
    @JSONField(name = "banAmend")
    @JsonProperty("banAmend")
    private Boolean banAmend;

    /**
     * 自成交保护模式
     * 默认为 cancel maker
     * cancel_maker,cancel_taker, cancel_both
     * Cancel both不支持FOK
     */
    @JSONField(name = "stpMode")
    @JsonProperty("stpMode")
    private StpMode stpMode;

    /**
     * 下单附带止盈止损信息
     */
    @JSONField(name = "attachAlgoOrds")
    @JsonProperty("attachAlgoOrds")
    private List<AttachAlgoOrder> attachAlgoOrds;

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AttachAlgoOrder {

        /**
         * 下单附带止盈止损时，客户自定义的策略订单ID
         * 字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。
         * 订单完全成交，下止盈止损委托单时，该值会传给algoClOrdId
         */
        @JSONField(name = "attachAlgoClOrdId")
        @JsonProperty("attachAlgoClOrdId")
        private String attachAlgoClOrdId;

        /**
         * 止盈触发价
         * 对于条件止盈单，如果填写此参数，必须填写 止盈委托价
         */
        @JSONField(name = "tpTriggerPx")
        @JsonProperty("tpTriggerPx")
        private BigDecimal tpTriggerPx;

        /**
         * 止盈委托价
         * 对于条件止盈单，如果填写此参数，必须填写 止盈触发价
         * 对于限价止盈单，需填写此参数，不需要填写止盈触发价
         */
        @JSONField(name = "tpOrdPx")
        @JsonProperty("tpOrdPx")
        private BigDecimal tpOrdPx;

        /**
         * 止盈订单类型
         * condition: 条件单
         * limit: 限价单
         * 默认为condition
         */
        @JSONField(name = "tpOrdKind")
        @JsonProperty("tpOrdKind")
        private TpOrderKind tpOrdKind;

        /**
         * 止损触发价，如果填写此参数，必须填写 止损委托价
         */
        @JSONField(name = "slTriggerPx")
        @JsonProperty("slTriggerPx")
        private BigDecimal slTriggerPx;

        /**
         * 止损委托价，如果填写此参数，必须填写 止损触发价
         * 委托价格为-1时，执行市价止损
         */
        @JSONField(name = "slOrdPx")
        @JsonProperty("slOrdPx")
        private BigDecimal slOrdPx;

        /**
         * 止盈触发价类型
         */
        @JSONField(name = "tpTriggerPxType")
        @JsonProperty("tpTriggerPxType")
        private TriggerPxType tpTriggerPxType;

        /**
         * 止损触发价类型
         */
        @JSONField(name = "slTriggerPxType")
        @JsonProperty("slTriggerPxType")
        private TriggerPxType slTriggerPxType;

        /**
         * 数量。仅适用于“多笔止盈”的止盈订单，且对于“多笔止盈”的止盈订单必填
         */
        @JSONField(name = "sz")
        @JsonProperty("sz")
        private BigDecimal sz;

        /**
         * 是否启用开仓价止损，仅适用于分批止盈的止损订单，第一笔止盈触发时，止损触发价格是否移动到开仓均价止损
         * 0：不开启，默认值
         * 1：开启，且止损触发价不能为空
         */
        @JSONField(name = "amendPxOnTriggerType")
        @JsonProperty("amendPxOnTriggerType")
        private Integer amendPxOnTriggerType;
    }

}
