package xyz.felh.okx.v5.entity.rest.trading.grid;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.rest.IOkxRestRsp;
import xyz.felh.okx.v5.entity.rest.IRestEntity;
import xyz.felh.okx.v5.enumeration.InstrumentType;
import xyz.felh.okx.v5.enumeration.PositionsSide;
import xyz.felh.okx.v5.enumeration.ws.MgnMode;

import java.math.BigDecimal;

/**
 * @author Forest Wang
 * @package xyz.felh.okx.v5.entity.rest.trading.grid
 * @class GridAlgoOrder
 * @email forestwanglin@gmail.cn
 * @date 2024/7/25
 */
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridAlgoOrderPosition implements IRestEntity, IOkxRestRsp {

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
     * 开仓均价
     */
    @JSONField(name = "avgPx")
    @JsonProperty("avgPx")
    private BigDecimal avgPx;

    /**
     * 保证金币种
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * Leverage
     * Only applicable to contract grid
     */
    @JSONField(name = "lever")
    @JsonProperty("lever")
    private Integer lever;

    /**
     * Estimated liquidation price
     * Only applicable to contract grid
     */
    @JSONField(name = "liqPx")
    @JsonProperty("liqPx")
    private BigDecimal liqPx;

    /**
     * 持仓方向
     * net：买卖模式
     */
    @JSONField(name = "posSide")
    @JsonProperty("posSide")
    private PositionsSide posSide;

    /**
     * 持仓数量
     */
    @JSONField(name = "pos")
    @JsonProperty("pos")
    private BigDecimal pos;

    /**
     * 保证金模式
     * cross：全仓
     * isolated：逐仓
     */
    @JSONField(name = "mgnMode")
    @JsonProperty("mgnMode")
    private MgnMode mgnMode;

    /**
     * 保证金率
     */
    @JSONField(name = "mgnRatio")
    @JsonProperty("mgnRatio")
    private BigDecimal mgnRatio;

    /**
     * 初始保证金
     */
    @JSONField(name = "imr")
    @JsonProperty("imr")
    private BigDecimal imr;

    /**
     * 维持保证金
     */
    @JSONField(name = "mmr")
    @JsonProperty("mmr")
    private BigDecimal mmr;

    /**
     * 未实现收益
     */
    @JSONField(name = "upl")
    @JsonProperty("upl")
    private BigDecimal upl;

    /**
     * 未实现收益率
     */
    @JSONField(name = "uplRatio")
    @JsonProperty("uplRatio")
    private BigDecimal uplRatio;

    /**
     * 最新成交价
     */
    @JSONField(name = "last")
    @JsonProperty("last")
    private BigDecimal last;

    /**
     * A仓位美金价值
     */
    @JSONField(name = "notionalUsd")
    @JsonProperty("notionalUsd")
    private BigDecimal notionalUsd;

    /**
     * 信号区
     * 分为5档，从1到5，数字越小代表adl强度越弱
     */
    @JSONField(name = "adl")
    @JsonProperty("adl")
    private Integer adl;

    /**
     * 标记价格
     */
    @JSONField(name = "markPx")
    @JsonProperty("markPx")
    private BigDecimal markPx;

}
