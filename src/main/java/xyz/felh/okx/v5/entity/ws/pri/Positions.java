package xyz.felh.okx.v5.entity.ws.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.IOkxWsObject;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.enumeration.InstrumentType;
import xyz.felh.okx.v5.enumeration.MgnMode;
import xyz.felh.okx.v5.enumeration.PositionsSide;
import xyz.felh.okx.v5.enumeration.TriggerPxType;

import java.math.BigDecimal;
import java.util.List;


@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Positions implements WsSubscribeEntity {

    /**
     * 产品类型
     */
    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

    /**
     * 保证金模式
     */
    @JSONField(name = "mgnMode")
    @JsonProperty("mgnMode")
    private MgnMode mgnMode;

    /**
     * 持仓ID
     */
    @JSONField(name = "posId")
    @JsonProperty("posId")
    private String posId;

    /**
     * 持仓方向
     */
    @JSONField(name = "posSide")
    @JsonProperty("posSide")
    private PositionsSide posSide;

    /**
     * 持仓数量，逐仓自主划转模式下，转入保证金后会产生pos为0的仓位
     */
    @JSONField(name = "pos")
    @JsonProperty("pos")
    private BigDecimal pos;

    /**
     * 持仓数量币种，仅适用于币币杠杆
     */
    @JSONField(name = "posCcy")
    @JsonProperty("posCcy")
    private String posCcy;

    /**
     * 可平仓数量，适用于 币币杠杆,交割/永续（开平仓模式），期权
     * 对于杠杆仓位，平仓时，杠杆还清负债后，余下的部分会视为币币交易，如果想要减少币币交易的数量，可通过"获取最大可用数量"接口获取只减仓的可用数量。
     */
    @JSONField(name = "availPos")
    @JsonProperty("availPos")
    private BigDecimal availPos;

    /**
     * 开仓平均价
     */
    @JSONField(name = "avgPx")
    @JsonProperty("avgPx")
    private BigDecimal avgPx;

    /**
     * 未实现收益（以标记价格计算）
     */
    @JSONField(name = "upl")
    @JsonProperty("upl")
    private BigDecimal upl;

    /**
     * 未实现收益率（以标记价格计算
     */
    @JSONField(name = "uplRatio")
    @JsonProperty("uplRatio")
    private BigDecimal uplRatio;

    /**
     * 以最新成交价格计算的未实现收益，主要做展示使用，实际值还是 upl
     */
    @JSONField(name = "uplLastPx")
    @JsonProperty("uplLastPx")
    private BigDecimal uplLastPx;

    /**
     * 以最新成交价格计算的未实现收益率
     */
    @JSONField(name = "uplRatioLastPx")
    @JsonProperty("uplRatioLastPx")
    private BigDecimal uplRatioLastPx;

    /**
     * 产品ID，如 BTC-USD-180216
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * 杠杆倍数，不适用于期权卖方
     */
    @JSONField(name = "lever")
    @JsonProperty("lever")
    private BigDecimal lever;

    /**
     * 预估强平价
     * 不适用于期权
     */
    @JSONField(name = "liqPx")
    @JsonProperty("liqPx")
    private BigDecimal liqPx;

    /**
     * 最新标记价格
     */
    @JSONField(name = "markPx")
    @JsonProperty("markPx")
    private BigDecimal markPx;

    /**
     * 初始保证金，仅适用于全仓
     */
    @JSONField(name = "imr")
    @JsonProperty("imr")
    private BigDecimal imr;

    /**
     * 保证金余额，仅适用于逐仓，可增减
     */
    @JSONField(name = "margin")
    @JsonProperty("margin")
    private BigDecimal margin;

    /**
     * 保证金率
     */
    @JSONField(name = "mgnRatio")
    @JsonProperty("mgnRatio")
    private BigDecimal mgnRatio;

    /**
     * 维持保证金
     */
    @JSONField(name = "mmr")
    @JsonProperty("mmr")
    private BigDecimal mmr;

    /**
     * 负债额，仅适用于币币杠杆
     */
    @JSONField(name = "liab")
    @JsonProperty("liab")
    private BigDecimal liab;

    /**
     * 负债币种，仅适用于币币杠杆
     */
    @JSONField(name = "liabCcy")
    @JsonProperty("liabCcy")
    private String liabCcy;

    /**
     * 利息，已经生成未扣利息
     */
    @JSONField(name = "interest")
    @JsonProperty("interest")
    private BigDecimal interest;

    /**
     * 最新成交ID
     */
    @JSONField(name = "tradeId")
    @JsonProperty("tradeId")
    private String tradeId;

    /**
     * 以美金价值为单位的持仓数量
     */
    @JSONField(name = "notionalUsd")
    @JsonProperty("notionalUsd")
    private BigDecimal notionalUsd;

    /**
     * 期权价值，仅适用于期权
     */
    @JSONField(name = "optVal")
    @JsonProperty("optVal")
    private BigDecimal optVal;

    /**
     * 逐仓杠杆负债对应平仓挂单的数量
     */
    @JSONField(name = "pendingCloseOrdLiabVal")
    @JsonProperty("pendingCloseOrdLiabVal")
    private BigDecimal pendingCloseOrdLiabVal;

    /**
     * 信号区，分为5档，从1到5，数字越小代表adl强度越弱
     */
    @JSONField(name = "adl")
    @JsonProperty("adl")
    private Integer adl;

    /**
     * 外部业务id，e.g. 体验券id
     */
    @JSONField(name = "bizRefId")
    @JsonProperty("bizRefId")
    private String bizRefId;

    /**
     * 外部业务类型
     */
    @JSONField(name = "bizRefType")
    @JsonProperty("bizRefType")
    private String bizRefType;

    /**
     * 占用保证金的币种
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 最新成交价
     */
    @JSONField(name = "last")
    @JsonProperty("last")
    private BigDecimal last;

    /**
     * 最新指数价格
     */
    @JSONField(name = "idxPx")
    @JsonProperty("idxPx")
    private BigDecimal idxPx;

    /**
     * 美金价格
     */
    @JSONField(name = "usdPx")
    @JsonProperty("usdPx")
    private BigDecimal usdPx;

    /**
     * 盈亏平衡价
     */
    @JSONField(name = "bePx")
    @JsonProperty("bePx")
    private BigDecimal bePx;

    /**
     * 美金本位持仓仓位delta，仅适用于期权
     */
    @JSONField(name = "deltaBS")
    @JsonProperty("deltaBS")
    private BigDecimal deltaBS;

    /**
     * 币本位持仓仓位delta，仅适用于期权
     */
    @JSONField(name = "deltaPA")
    @JsonProperty("deltaPA")
    private BigDecimal deltaPA;

    /**
     * 美金本位持仓仓位gamma，仅适用于期权
     */
    @JSONField(name = "gammaBS")
    @JsonProperty("gammaBS")
    private BigDecimal gammaBS;

    /**
     * 币本位持仓仓位gamma，仅适用于期权
     */
    @JSONField(name = "gammaPA")
    @JsonProperty("gammaPA")
    private BigDecimal gammaPA;

    /**
     * 美金本位持仓仓位theta，仅适用于期权
     */
    @JSONField(name = "thetaBS")
    @JsonProperty("thetaBS")
    private BigDecimal thetaBS;

    /**
     * 币本位持仓仓位theta，仅适用于期权
     */
    @JSONField(name = "thetaPA")
    @JsonProperty("thetaPA")
    private BigDecimal thetaPA;

    /**
     * 美金本位持仓仓位vega，仅适用于期权
     */
    @JSONField(name = "vegaBS")
    @JsonProperty("vegaBS")
    private BigDecimal vegaBS;

    /**
     * 币本位持仓仓位vega，仅适用于期权
     */
    @JSONField(name = "vegaPA")
    @JsonProperty("vegaPA")
    private BigDecimal vegaPA;

    /**
     * 现货对冲占用数量
     * 适用于组合保证金模式
     */
    @JSONField(name = "spotInUseAmt")
    @JsonProperty("spotInUseAmt")
    private BigDecimal spotInUseAmt;

    /**
     * 现货对冲占用币种，如 BTC
     * 适用于组合保证金模式
     */
    @JSONField(name = "spotInUseCcy")
    @JsonProperty("spotInUseCcy")
    private String spotInUseCcy;

    /**
     * 已实现收益
     */
    @JSONField(name = "realizedPnl")
    @JsonProperty("realizedPnl")
    private BigDecimal realizedPnl;

    /**
     * 平仓订单累计收益额
     */
    @JSONField(name = "pnl")
    @JsonProperty("pnl")
    private BigDecimal pnl;

    /**
     * 累计手续费金额，正数代表平台返佣 ，负数代表平台扣除
     */
    @JSONField(name = "fee")
    @JsonProperty("fee")
    private BigDecimal fee;

    /**
     * 累计资金费用
     */
    @JSONField(name = "fundingFee")
    @JsonProperty("fundingFee")
    private BigDecimal fundingFee;

    /**
     * 累计爆仓罚金，有值时为负数。
     */
    @JSONField(name = "liqPenalty")
    @JsonProperty("liqPenalty")
    private BigDecimal liqPenalty;

    /**
     * 平仓策略委托订单。调用策略委托下单，且closeFraction=1 时，该数组才会有值。
     */
    @JSONField(name = "closeOrderAlgo")
    @JsonProperty("closeOrderAlgo")
    private List<CloseOrderAlgo> closeOrderAlgo;

    /**
     * 持仓创建时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "cTime")
    @JsonProperty("cTime")
    private Long cTime;

    /**
     * 最近一次持仓更新时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "uTime")
    @JsonProperty("uTime")
    private Long uTime;

    /**
     * 持仓信息的推送时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "pTime")
    @JsonProperty("pTime")
    private Long pTime;

    @Data
    public static class CloseOrderAlgo implements IOkxWsObject {

        /**
         * 策略委托单ID
         */
        @JSONField(name = "algoId")
        @JsonProperty("algoId")
        private String algoId;

        /**
         * 策略委托触发时，平仓的百分比。1 代表100%
         */
        @JSONField(name = "closeFraction")
        @JsonProperty("closeFraction")
        private BigDecimal closeFraction;

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
         * 止盈委托价
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

    }


}