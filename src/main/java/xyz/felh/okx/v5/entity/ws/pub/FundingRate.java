package xyz.felh.okx.v5.entity.ws.pub;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.enumeration.ws.FundingRateMethod;
import xyz.felh.okx.v5.enumeration.ws.FundingRateSettleState;
import xyz.felh.okx.v5.enumeration.ws.InstrumentType;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class FundingRate implements WsSubscribeEntity {

    /**
     * 产品类型，SWAP
     */
    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

    /**
     * 产品ID，如 LTC-USD-SWAP
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * 资金费收取逻辑
     */
    @JSONField(name = "method")
    @JsonProperty("method")
    private FundingRateMethod method;

    /**
     * 资金费率
     */
    @JSONField(name = "fundingRate")
    @JsonProperty("fundingRate")
    private BigDecimal fundingRate;

    /**
     * 最新的到期结算的资金费时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "fundingTime")
    @JsonProperty("fundingTime")
    private Long fundingTime;

    /**
     * 下一期预测资金费率
     */
    @JSONField(name = "nextFundingRate")
    @JsonProperty("nextFundingRate")
    private BigDecimal nextFundingRate;

    /**
     * 下一期资金费时间，Unix时间戳的毫秒数格式，如 1622851200000
     */
    @JSONField(name = "nextFundingTime")
    @JsonProperty("nextFundingTime")
    private Long nextFundingTime;

    /**
     * 下一期的预测资金费率下限
     */
    @JSONField(name = "minFundingRate")
    @JsonProperty("minFundingRate")
    private BigDecimal minFundingRate;

    /**
     * 下一期的预测资金费率上限
     */
    @JSONField(name = "maxFundingRate")
    @JsonProperty("maxFundingRate")
    private BigDecimal maxFundingRate;

    /**
     * 资金费率结算状态
     * processing：结算中
     * settled：已结算
     */
    @JSONField(name = "settState")
    @JsonProperty("settState")
    private FundingRateSettleState settState;

    /**
     * 若 settState = processing，该字段代表用于本轮结算的资金费率；若 settState = settled，该字段代表用于上轮结算的资金费率
     */
    @JSONField(name = "settFundingRate")
    @JsonProperty("settFundingRate")
    private BigDecimal settFundingRate;

    /**
     * 溢价，为合约的中间价和指数价格的差异
     */
    @JSONField(name = "premium")
    @JsonProperty("premium")
    private BigDecimal premium;

    /**
     * 数据更新时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "ts")
    @JsonProperty("ts")
    private Long ts;

}
