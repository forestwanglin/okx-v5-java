package xyz.felh.okx.v5.entity.ws.biz;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.enumeration.PositionsSide;
import xyz.felh.okx.v5.enumeration.ws.InstrumentType;
import xyz.felh.okx.v5.enumeration.ws.MgnMode;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class GridPositions implements WsSubscribeEntity {

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
     * verage open price
     */
    @JSONField(name = "avgPx")
    @JsonProperty("avgPx")
    private BigDecimal avgPx;

    /**
     * Margin currency
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * Leverage
     */
    @JSONField(name = "lever")
    @JsonProperty("lever")
    private String lever;

    /**
     * Estimated liquidation price
     */
    @JSONField(name = "liqPx")
    @JsonProperty("liqPx")
    private BigDecimal liqPx;

    /**
     * Position side
     * net
     */
    @JSONField(name = "posSide")
    @JsonProperty("posSide")
    private PositionsSide posSide;

    /**
     * Quantity of positions
     */
    @JSONField(name = "pos")
    @JsonProperty("pos")
    private BigDecimal pos;

    /**
     * Margin mode
     * cross
     * isolated
     */
    @JSONField(name = "mgnMode")
    @JsonProperty("mgnMode")
    private MgnMode mgnMode;

    /**
     * Margin ratio
     */
    @JSONField(name = "mgnRatio")
    @JsonProperty("mgnRatio")
    private BigDecimal mgnRatio;

    /**
     * Initial margin requirement
     */
    @JSONField(name = "imr")
    @JsonProperty("imr")
    private BigDecimal imr;

    /**
     * Maintenance margin requirement
     */
    @JSONField(name = "mmr")
    @JsonProperty("mmr")
    private BigDecimal mmr;

    /**
     * Unrealized profit and loss
     */
    @JSONField(name = "upl")
    @JsonProperty("upl")
    private BigDecimal upl;

    /**
     * Unrealized profit and loss ratio
     */
    @JSONField(name = "uplRatio")
    @JsonProperty("uplRatio")
    private BigDecimal uplRatio;

    /**
     * Latest traded price
     */
    @JSONField(name = "last")
    @JsonProperty("last")
    private BigDecimal last;

    /**
     * Notional value of positions in USD
     */
    @JSONField(name = "notionalUsd")
    @JsonProperty("notionalUsd")
    private BigDecimal notionalUsd;

    /**
     * Auto decrease line, signal area
     * Divided into 5 levels, from 1 to 5, the smaller the number, the weaker the adl intensity.
     */
    @JSONField(name = "adl")
    @JsonProperty("adl")
    private Integer adl;

    /**
     * Mark price
     */
    @JSONField(name = "markPx")
    @JsonProperty("markPx")
    private BigDecimal markPx;

    /**
     * Push time of positions information, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "pTime")
    @JsonProperty("pTime")
    private Long pTime;

}
