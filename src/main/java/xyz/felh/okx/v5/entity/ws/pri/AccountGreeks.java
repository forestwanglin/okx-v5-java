package xyz.felh.okx.v5.entity.ws.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AccountGreeks implements WsSubscribeEntity {

    /**
     * 美金本位账户资产delta
     */
    @JSONField(name = "deltaBS")
    @JsonProperty("deltaBS")
    private BigDecimal deltaBS;

    /**
     * 币本位账户资产delta
     */
    @JSONField(name = "deltaPA")
    @JsonProperty("deltaPA")
    private BigDecimal deltaPA;

    /**
     * 美金本位账户资产gamma，仅适用于期权
     */
    @JSONField(name = "gammaBS")
    @JsonProperty("gammaBS")
    private BigDecimal gammaBS;

    /**
     * 币本位账户资产gamma，仅适用于期权
     */
    @JSONField(name = "gammaPA")
    @JsonProperty("gammaPA")
    private BigDecimal gammaPA;

    /**
     * 美金本位账户资产theta，仅适用于期权
     */
    @JSONField(name = "thetaBS")
    @JsonProperty("thetaBS")
    private BigDecimal thetaBS;

    /**
     * 币本位账户资产theta，仅适用于期权
     */
    @JSONField(name = "thetaPA")
    @JsonProperty("thetaPA")
    private BigDecimal thetaPA;

    /**
     * 美金本位账户资产vega，仅适用于期权
     */
    @JSONField(name = "vegaBS")
    @JsonProperty("vegaBS")
    private BigDecimal vegaBS;

    /**
     * 币本位账户资产vega，仅适用于期权
     */
    @JSONField(name = "vegaPA")
    @JsonProperty("vegaPA")
    private BigDecimal vegaPA;

    /**
     * 币种
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 获取greeks的时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "ts")
    @JsonProperty("ts")
    private Long ts;

}
