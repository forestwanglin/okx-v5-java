package xyz.felh.okx.v5.entity.ws.pub;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.enumeration.InstrumentType;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AdlWarning implements WsSubscribeEntity {

    /**
     * 产品类型
     */
    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

    /**
     * 交易品种
     */
    @JSONField(name = "instFamily")
    @JsonProperty("instFamily")
    private String instFamily;

    /**
     * 状态
     * normal：普通状态
     * warning：预警状态
     * adl：已开启自动减仓
     */
    @JSONField(name = "state")
    @JsonProperty("state")
    private String state;

    /**
     * 实时风险准备金余额
     */
    @JSONField(name = "bal")
    @JsonProperty("bal")
    private BigDecimal bal;

    /**
     * 风险准备金余额对应币种
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 过去八小时内的风险准备金余额最大值
     * 仅在状态为warning及adl时推送，状态为normal时推送空字符串""
     */
    @JSONField(name = "maxBal")
    @JsonProperty("maxBal")
    private BigDecimal maxBal;

    /**
     * 过去八小时内风险准备金余额最大值对应的时间戳，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "maxBalTs")
    @JsonProperty("maxBalTs")
    private Long maxBalTs;

    /**
     * 风险准备金实时下降率（bal与maxBal相比较）
     * 仅在状态为warning及adl时推送，状态为normal时推送空字符串""
     */
    @JSONField(name = "decRate")
    @JsonProperty("decRate")
    private BigDecimal decRate;

    /**
     * 关于自动减仓的事件
     * rate_adl_start：由于风险准备金下降率过高造成的自动减仓开始
     * bal_adl_start：由于风险准备金余额下降过高造成的自动减仓开始
     * adl_end：自动减仓结束
     * 两个开启条件同时触发时，仅推送bal_adl_start
     */
    @JSONField(name = "adlType")
    @JsonProperty("adlType")
    private Type adlType;

    /**
     * 触发自动减仓的风险准备金余额
     */
    @JSONField(name = "adlBal")
    @JsonProperty("adlBal")
    private BigDecimal adlBal;

    /**
     * 触发自动减仓的风险准备金下降率
     */
    @JSONField(name = "adlRate")
    @JsonProperty("adlRate")
    private BigDecimal adlRate;

    /**
     * 自动减仓结束的风险准备金余额
     */
    @JSONField(name = "adlRecBal")
    @JsonProperty("adlRecBal")
    private BigDecimal adlRecBal;

    /**
     * 自动减仓结束的风险准备金下降率
     */
    @JSONField(name = "adlRecRate")
    @JsonProperty("adlRecRate")
    private BigDecimal adlRecRate;

    /**
     * 数据更新时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "ts")
    @JsonProperty("ts")
    private Long ts;

    @Getter
    public enum Type {
        /**
         * 由于风险准备金下降率过高造成的自动减仓开始
         */
        RATE_ADL_START("rate_adl_start"),
        /**
         * 由于风险准备金余额下降过高造成的自动减仓开始
         */
        BAL_ADL_START("bal_adl_start"),
        /**
         * 自动减仓结束
         */
        ADL_END("adl_end");

        private final String value;

        Type(final String value) {
            this.value = value;
        }

        @JsonValue
        public String value() {
            return value;
        }
    }

}
