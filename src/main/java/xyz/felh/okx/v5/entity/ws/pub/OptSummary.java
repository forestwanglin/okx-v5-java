package xyz.felh.okx.v5.entity.ws.pub;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.enumeration.InstrumentType;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class OptSummary implements WsSubscribeEntity {

    /**
     * 产品类型， OPTION
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
     * 标的指数
     */
    @JSONField(name = "uly")
    @JsonProperty("uly")
    private String uly;

    /**
     * 期权价格对uly价格的敏感度
     */
    @JSONField(name = "uly")
    @JsonProperty("uly")
    private BigDecimal delta;

    /**
     * delta对uly价格的敏感度
     */
    @JSONField(name = "gamma")
    @JsonProperty("gamma")
    private BigDecimal gamma;

    /**
     * 期权价格对隐含波动率的敏感度
     */
    @JSONField(name = "vega")
    @JsonProperty("vega")
    private BigDecimal vega;

    /**
     * 期权价格对剩余期限的敏感度
     */
    @JSONField(name = "theta")
    @JsonProperty("theta")
    private BigDecimal theta;

    /**
     * BS模式下期权价格对uly价格的敏感度
     */
    @JSONField(name = "deltaBS")
    @JsonProperty("deltaBS")
    private BigDecimal deltaBS;

    /**
     * BS模式下delta对uly价格的敏感度
     */
    @JSONField(name = "gammaBS")
    @JsonProperty("gammaBS")
    private BigDecimal gammaBS;

    /**
     * BS模式下期权价格对隐含波动率的敏感度
     */
    @JSONField(name = "vegaBS")
    @JsonProperty("vegaBS")
    private BigDecimal vegaBS;

    /**
     * BS模式下期权价格对剩余期限的敏感度
     */
    @JSONField(name = "thetaBS")
    @JsonProperty("thetaBS")
    private BigDecimal thetaBS;

    /**
     * 杠杆倍数
     */
    @JSONField(name = "lever")
    @JsonProperty("lever")
    private BigDecimal lever;

    /**
     * 标记波动率
     */
    @JSONField(name = "markVol")
    @JsonProperty("markVol")
    private BigDecimal markVol;

    /**
     * bid波动率
     */
    @JSONField(name = "bidVol")
    @JsonProperty("bidVol")
    private BigDecimal bidVol;

    /**
     * ask波动率
     */
    @JSONField(name = "askVol")
    @JsonProperty("askVol")
    private BigDecimal askVol;

    /**
     *
     */
    @JSONField(name = "realVol")
    @JsonProperty("realVol")
    private BigDecimal realVol;

    /**
     *
     */
    @JSONField(name = "volLv")
    @JsonProperty("volLv")
    private BigDecimal volLv;

    /**
     * 远期价格
     */
    @JSONField(name = "fwdPx")
    @JsonProperty("fwdPx")
    private BigDecimal fwdPx;

    /**
     * 数据更新时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "ts")
    @JsonProperty("ts")
    private Long ts;

}
