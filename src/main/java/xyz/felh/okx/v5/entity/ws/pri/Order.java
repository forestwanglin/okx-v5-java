package xyz.felh.okx.v5.entity.ws.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.enumeration.*;
import xyz.felh.okx.v5.enumeration.ws.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Order implements WsSubscribeEntity {

    /**
     * 产品类型
     */
    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

    /**
     * 产品ID
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * 保证金币种，仅适用于单币种保证金账户下的全仓币币杠杆订单
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 订单ID
     */
    @JSONField(name = "ordId")
    @JsonProperty("ordId")
    private String ordId;

    /**
     * 由用户设置的订单ID来识别您的订单
     */
    @JSONField(name = "clOrdId")
    @JsonProperty("clOrdId")
    private String clOrdId;

    /**
     * 订单标签
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;

    /**
     * 委托价格，对于期权，以币(如BTC, ETH)为单位
     */
    @JSONField(name = "px")
    @JsonProperty("px")
    private BigDecimal px;

    /**
     * 期权价格，以USD为单位
     * 仅适用于期权，其他业务线返回空字符串""
     */
    @JSONField(name = "pxUsd")
    @JsonProperty("pxUsd")
    private BigDecimal pxUsd;

    /**
     * 期权订单的隐含波动率
     * 仅适用于期权，其他业务线返回空字符串""
     */
    @JSONField(name = "pxVol")
    @JsonProperty("pxVol")
    private BigDecimal pxVol;

    /**
     * 期权的价格类型
     * px：代表按价格下单，单位为币 (请求参数 px 的数值单位是BTC或ETH)
     * pxVol：代表按pxVol下单
     * pxUsd：代表按照pxUsd下单，单位为USD (请求参数px 的数值单位是USD)
     */
    @JSONField(name = "pxType")
    @JsonProperty("pxType")
    private PriceType pxType;

    /**
     * 原始委托数量，币币/币币杠杆，以币为单位；交割/永续/期权 ，以张为单位
     */
    @JSONField(name = "sz")
    @JsonProperty("sz")
    private BigDecimal sz;

    /**
     * 委托单预估美元价值
     */
    @JSONField(name = "notionalUsd")
    @JsonProperty("notionalUsd")
    private BigDecimal notionalUsd;

    /**
     * 委托单已成交的美元价值
     */
    @JSONField(name = "fillNotionalUsd")
    @JsonProperty("fillNotionalUsd")
    private BigDecimal fillNotionalUsd;

    /**
     * 订单类型
     * market：市价单
     * limit：限价单
     * post_only：只做maker单
     * fok：全部成交或立即取消单
     * ioc：立即成交并取消剩余单
     * optimal_limit_ioc：市价委托立即成交并取消剩余（仅适用交割、永续）
     * mmp：做市商保护(仅适用于组合保证金账户模式下的期权订单)
     * mmp_and_post_only：做市商保护且只做maker单(仅适用于组合保证金账户模式下的期权订单)
     * op_fok：期权简选（全部成交或立即取消）
     */
    @JSONField(name = "ordType")
    @JsonProperty("ordType")
    private OrderType ordType;

    /**
     * 订单方向，buy sell
     */
    @JSONField(name = "side")
    @JsonProperty("side")
    private Side side;

    /**
     * 持仓方向
     * long：开平仓模式开多
     * short：开平仓模式开空
     * net：买卖模式
     */
    @JSONField(name = "posSide")
    @JsonProperty("posSide")
    private PositionsSide posSide;

    /**
     * 交易模式
     * 保证金模式 isolated：逐仓 cross：全仓
     * 非保证金模式 cash：现金
     */
    @JSONField(name = "tdMode")
    @JsonProperty("tdMode")
    private TdMode tdMode;

    /**
     * 市价单委托数量sz的单位
     * base_ccy: 交易货币 quote_ccy：计价货币
     */
    @JSONField(name = "tgtCcy")
    @JsonProperty("tgtCcy")
    private String tgtCcy;

    /**
     * 最新成交价格
     */
    @JSONField(name = "fillPx")
    @JsonProperty("fillPx")
    private BigDecimal fillPx;

    /**
     * 最新成交ID
     */
    @JSONField(name = "tradeId")
    @JsonProperty("tradeId")
    private String tradeId;

    /**
     * 最新成交数量
     * 对于币币和杠杆，单位为交易货币，如 BTC-USDT, 单位为 BTC；对于市价单，无论tgtCcy是base_ccy，还是quote_ccy，单位均为交易货币；
     * 对于交割、永续以及期权，单位为张。
     */
    @JSONField(name = "fillSz")
    @JsonProperty("fillSz")
    private BigDecimal fillSz;

    /**
     * 最新成交收益，适用于有成交的平仓订单。其他情况均为0。
     */
    @JSONField(name = "fillPnl")
    @JsonProperty("fillPnl")
    private BigDecimal fillPnl;

    /**
     * 最新成交时间
     */
    @JSONField(name = "fillTime")
    @JsonProperty("fillTime")
    private Long fillTime;

    /**
     * 最新一笔成交的手续费金额或者返佣金额：
     * 手续费扣除 为 ‘负数’，如 -0.01 ；
     * 手续费返佣 为 ‘正数’，如 0.01
     */
    @JSONField(name = "fillFee")
    @JsonProperty("fillFee")
    private BigDecimal fillFee;

    /**
     * 最新一笔成交的手续费币种或者返佣币种。
     * 如果fillFee小于0，为手续费币种；如果fillFee大于等于0，为返佣币种
     */
    @JSONField(name = "fillFeeCcy")
    @JsonProperty("fillFeeCcy")
    private String fillFeeCcy;

    /**
     * 成交时的隐含波动率仅适用于期权，其他业务线返回空字符串""
     */
    @JSONField(name = "fillPxVol")
    @JsonProperty("fillPxVol")
    private BigDecimal fillPxVol;

    /**
     * 成交时的期权价格，以USD为单位仅适用于期权，其他业务线返回空字符串""
     */
    @JSONField(name = "fillPxUsd")
    @JsonProperty("fillPxUsd")
    private BigDecimal fillPxUsd;

    /**
     * 成交时的标记波动率，仅适用于期权，其他业务线返回空字符串""
     */
    @JSONField(name = "fillMarkVol")
    @JsonProperty("fillMarkVol")
    private BigDecimal fillMarkVol;

    /**
     * 成交时的远期价格，仅适用于期权，其他业务线返回空字符串""
     */
    @JSONField(name = "fillFwdPx")
    @JsonProperty("fillFwdPx")
    private BigDecimal fillFwdPx;

    /**
     * 成交时的标记价格，仅适用于 交割/永续/期权
     */
    @JSONField(name = "fillMarkPx")
    @JsonProperty("fillMarkPx")
    private BigDecimal fillMarkPx;

    /**
     * 最新一笔成交的流动性方向 T：taker M：maker
     */
    @JSONField(name = "execType")
    @JsonProperty("execType")
    private ExecType execType;

    /**
     * 累计成交数量
     * 对于币币和杠杆，单位为交易货币，如 BTC-USDT, 单位为 BTC；对于市价单，无论tgtCcy是base_ccy，还是quote_ccy，单位均为交易货币；
     * 对于交割、永续以及期权，单位为张。
     */
    @JSONField(name = "accFillSz")
    @JsonProperty("accFillSz")
    private BigDecimal accFillSz;

    /**
     * 成交均价，如果成交数量为0，该字段也为0
     */
    @JSONField(name = "avgPx")
    @JsonProperty("avgPx")
    private BigDecimal avgPx;

    /**
     * 订单状态
     * canceled：撤单成功
     * live：等待成交
     * partially_filled：部分成交
     * filled：完全成交
     * mmp_canceled：做市商保护机制导致的自动撤单
     */
    @JSONField(name = "state")
    @JsonProperty("state")
    private OrderState state;

    /**
     * 杠杆倍数，0.01到125之间的数值，仅适用于 币币杠杆/交割/永续
     */
    @JSONField(name = "lever")
    @JsonProperty("lever")
    private BigDecimal lever;

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
     * 止盈委托价，止盈委托价格为-1时，执行市价止盈
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
     * 止损委托价，止损委托价格为-1时，执行市价止损
     */
    @JSONField(name = "slOrdPx")
    @JsonProperty("slOrdPx")
    private BigDecimal slOrdPx;

    /**
     * 下单附带止盈止损信息
     */
    @JSONField(name = "attachAlgoOrds")
    @JsonProperty("attachAlgoOrds")
    private List<AttachAloOrder> attachAlgoOrds;

    /**
     * 自成交保护模式
     */
    @JSONField(name = "stpMode")
    @JsonProperty("stpMode")
    private StpMode stpMode;

    /**
     * 交易手续费币种
     * 币币/币币杠杆：如果是买的话，收取的就是交易币；如果是卖的话，收取的就是计价币。
     * 交割/永续/期权 收取的就是保证金
     */
    @JSONField(name = "feeCcy")
    @JsonProperty("feeCcy")
    private String feeCcy;

    /**
     * 订单交易累计的手续费与返佣
     * 对于币币和杠杆，为订单交易累计的手续费，平台向用户收取的交易手续费，为负数。如： -0.01
     * 对于交割、永续和期权，为订单交易累计的手续费和返佣
     */
    @JSONField(name = "fee")
    @JsonProperty("fee")
    private BigDecimal fee;

    /**
     * 返佣金币种 ，如果没有返佣金，该字段为“”
     */
    @JSONField(name = "rebateCcy")
    @JsonProperty("rebateCcy")
    private String rebateCcy;

    /**
     * 返佣累计金额，仅适用于币币和杠杆，平台向达到指定lv交易等级的用户支付的挂单奖励（返佣），如果没有返佣金，该字段为“”
     */
    @JSONField(name = "rebate")
    @JsonProperty("rebate")
    private BigDecimal rebate;

    /**
     * 收益，适用于有成交的平仓订单，其他情况均为0
     * 对于合约全仓爆仓，将包含相应强平惩罚金
     */
    @JSONField(name = "pnl")
    @JsonProperty("pnl")
    private BigDecimal pnl;

    /**
     * 订单来源
     * 6：计划委托策略触发后的生成的普通单
     * 7：止盈止损策略触发后的生成的普通单
     * 13：策略委托单触发后的生成的普通单
     * 24：移动止盈止损策略触发后的生成的普通单
     */
    @JSONField(name = "source")
    @JsonProperty("source")
    private Integer source;

    /**
     * 订单取消的来源
     * 有效值及对应的含义是：
     * 0: 已撤单：系统撤单
     * 1: 用户主动撤单
     * 2: 已撤单：预减仓撤单，用户保证金不足导致挂单被撤回
     * 3: 已撤单：风控撤单，用户保证金不足有爆仓风险，导致挂单被撤回
     * 4: 已撤单：币种借币量达到平台硬顶，系统已撤回该订单
     * 6: 已撤单：触发 ADL 撤单，用户保证金率较低且有爆仓风险，导致挂单被撤回
     * 7: 已撤单：交割合约到期
     * 9: 已撤单：扣除资金费用后可用余额不足，系统已撤回该订单
     * 13: 已撤单：FOK 委托订单未完全成交，导致挂单被完全撤回
     * 14: 已撤单：IOC 委托订单未完全成交，仅部分成交，导致部分挂单被撤回
     * 15: 已撤单：该订单委托价不在限价范围内
     * 17: 已撤单：平仓单被撤单，由于仓位已被市价全平
     * 20: 系统倒计时撤单
     * 21: 已撤单：相关仓位被完全平仓，系统已撤销该止盈止损订单
     * 22, 23: 已撤单：只减仓订单仅允许减少仓位数量，系统已撤销该订单
     * 27: 成交滑点超过5%，触发成交差价保护导致系统撤单
     * 31: 当前只挂单订单 (Post only) 将会吃掉挂单深度
     * 32: 自成交保护
     * 33: 当前 taker 订单匹配的订单数量超过最大限制
     * 36: 关联止损被触发，撤销限价止盈
     * 37: 关联止损被撤销，撤销限价止盈
     * 38: 您已撤销做市商保护 (MMP) 类型订单
     * 39: 因做市商保护 (MMP) 被触发，该类型订单已被撤销
     */
    @JSONField(name = "cancelSource")
    @JsonProperty("cancelSource")
    private Integer cancelSource;

    /**
     * 订单修改的来源
     * 1: 用户主动改单，改单成功
     * 2: 用户主动改单，并且当前这笔订单被只减仓修改，改单成功
     * 3: 用户主动下单，并且当前这笔订单被只减仓修改，改单成功
     * 4: 用户当前已存在的挂单（非当前操作的订单），被只减仓修改，改单成功
     * 5：期权 px, pxVol 或 pxUsd 的跟随变动导致的改单，比如 iv=60，usd，px 锚定iv=60 时，usd, px 产生变动时的改单
     */
    @JSONField(name = "amendSource")
    @JsonProperty("amendSource")
    private Integer amendSource;

    /**
     * 订单种类分类
     */
    @JSONField(name = "category")
    @JsonProperty("category")
    private OrderCategory category;

    /**
     * 是否为限价止盈，true 或 false.
     */
    @JSONField(name = "isTpLimit")
    @JsonProperty("isTpLimit")
    private Boolean isTpLimit;

    /**
     * 订单更新时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "uTime")
    @JsonProperty("uTime")
    private Long uTime;

    /**
     * 订单创建时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "cTime")
    @JsonProperty("cTime")
    private Long cTime;

    /**
     * 修改订单时使用的request ID，如果没有修改，该字段为""
     */
    @JSONField(name = "reqId")
    @JsonProperty("reqId")
    private String reqId;

    /**
     * 修改订单的结果
     * -1：失败
     * 0：成功
     * 1：自动撤单（修改请求返回成功但最终改单失败导致自动撤销）
     * 2: 自动改单成功，仅适用于期权pxUsd和pxVol订单的自动改单
     * 通过API修改订单时，如果cxlOnFail设置为true且修改返回结果为失败时，则返回 ""
     * 通过API修改订单时，如果修改返回结果为成功但修改最终失败后，当cxlOnFail设置为false时返回 -1;当cxlOnFail设置为true时则返回1
     * 通过Web/APP修改订单时，如果修改失败后，则返回-1
     */
    @JSONField(name = "amendResult")
    @JsonProperty("amendResult")
    private Integer amendResult;

    /**
     * 是否只减仓，true 或 false
     */
    @JSONField(name = "reduceOnly")
    @JsonProperty("reduceOnly")
    private Boolean reduceOnly;

    /**
     * 一键借币类型，仅适用于杠杆逐仓的一键借币模式
     * manual：手动，auto_borrow：自动借币，auto_repay：自动还币
     */
    @JSONField(name = "quickMgnType")
    @JsonProperty("quickMgnType")
    private QuickMgnType quickMgnType;

    /**
     * 客户自定义策略订单ID。策略订单触发，且策略单有algoClOrdId时有值，否则为"",
     */
    @JSONField(name = "algoClOrdId")
    @JsonProperty("algoClOrdId")
    private String algoClOrdId;

    /**
     * 策略委托单ID，策略订单触发时有值，否则为""
     */
    @JSONField(name = "algoId")
    @JsonProperty("algoId")
    private String algoId;

    /**
     * 最新成交价
     */
    @JSONField(name = "lastPx")
    @JsonProperty("lastPx")
    private BigDecimal lastPx;

    /**
     * 错误码，默认为0
     */
    @JSONField(name = "code")
    @JsonProperty("code")
    private Integer code;

    /**
     * 错误消息，默认为""
     */
    @JSONField(name = "msg")
    @JsonProperty("msg")
    private String msg;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AttachAloOrder {

        /**
         * 附带止盈止损的订单ID，订单完全成交，下止盈止损委托单时，该值不会传给 algoId
         */
        @JSONField(name = "attachAlgoId")
        @JsonProperty("attachAlgoId")
        private String attachAlgoId;

        /**
         * 下单附带止盈止损时，客户自定义的策略订单ID
         */
        @JSONField(name = "attachAlgoClOrdId")
        @JsonProperty("attachAlgoClOrdId")
        private String attachAlgoClOrdId;

        /**
         * 止盈订单类型
         */
        @JSONField(name = "tpOrdKind")
        @JsonProperty("tpOrdKind")
        private TpOrderKind tpOrdKind;

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
        /**
         * 张数。仅适用于“多笔止盈”的止盈订单
         */
        @JSONField(name = "sz")
        @JsonProperty("sz")
        private BigDecimal sz;

        /**
         * 是否启用开仓价止损，仅适用于分批止盈的止损订单
         * 0：不开启，默认值
         * 1：开启
         */
        @JSONField(name = "amendPxOnTriggerType")
        @JsonProperty("amendPxOnTriggerType")
        private Integer amendPxOnTriggerType;

        /**
         * 止损订单信息，仅适用于双向止盈止损的限价止盈单
         */
        @JSONField(name = "linkedAlgoOrd")
        @JsonProperty("linkedAlgoOrd")
        private LinkedAlgoOrder linkedAlgoOrd;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LinkedAlgoOrder {
        /**
         * 策略订单唯一标识
         */
        @JSONField(name = "algoId")
        @JsonProperty("algoId")
        private String algoId;
    }
}
