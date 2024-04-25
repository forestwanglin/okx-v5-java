package xyz.felh.okx.v5.entity.rest.account;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.rest.IOkxRestRsp;
import xyz.felh.okx.v5.entity.rest.IRestEntity;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Balance implements IRestEntity, IOkxRestRsp {

    /**
     * 账户信息的更新时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "uTime")
    @JsonProperty("uTime")
    private Long uTime;

    /**
     * 美金层面权益
     */
    @JSONField(name = "totalEq")
    @JsonProperty("totalEq")
    private BigDecimal totalEq;

    /**
     * 美金层面逐仓仓位权益
     * 适用于单币种保证金模式/跨币种保证金模式/组合保证金模式
     */
    @JSONField(name = "isoEq")
    @JsonProperty("isoEq")
    private BigDecimal isoEq;

    /**
     * 美金层面有效保证金
     * 适用于跨币种保证金模式/组合保证金模式
     */
    @JSONField(name = "adjEq")
    @JsonProperty("adjEq")
    private BigDecimal adjEq;

    /**
     * 美金层面全仓挂单占用保证金
     * 仅适用于跨币种保证金模式
     */
    @JSONField(name = "ordFroz")
    @JsonProperty("ordFroz")
    private BigDecimal ordFroz;

    /**
     * 美金层面占用保证金
     * 适用于跨币种保证金模式/组合保证金模式
     */
    @JSONField(name = "imr")
    @JsonProperty("imr")
    private BigDecimal imr;

    /**
     * 美金层面维持保证金
     * 适用于跨币种保证金模式/组合保证金模式
     */
    @JSONField(name = "mmr")
    @JsonProperty("mmr")
    private BigDecimal mmr;

    /**
     * 账户美金层面潜在借币占用保证金
     * 仅适用于跨币种保证金模式/组合保证金模式。在其他账户模式下为""。
     */
    @JSONField(name = "borrowFroz")
    @JsonProperty("borrowFroz")
    private BigDecimal borrowFroz;

    /**
     * 美金层面保证金率
     * 适用于跨币种保证金模式/组合保证金模式
     */
    @JSONField(name = "mgnRatio")
    @JsonProperty("mgnRatio")
    private BigDecimal mgnRatio;

    /**
     * 以美金价值为单位的持仓数量，即仓位美金价值
     * 适用于跨币种保证金模式/组合保证金模式
     */
    @JSONField(name = "notionalUsd")
    @JsonProperty("notionalUsd")
    private BigDecimal notionalUsd;

    /**
     * 账户层面全仓未实现盈亏（美元单位）
     * 适用于跨币种保证金模式/组合保证金模式
     */
    @JSONField(name = "upl")
    @JsonProperty("upl")
    private BigDecimal upl;

    /**
     * 各币种资产详细信息
     */
    @JSONField(name = "details")
    @JsonProperty("details")
    private List<Detail> details;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {

        /**
         * 币种
         */
        @JSONField(name = "ccy")
        @JsonProperty("ccy")
        private String ccy;

        /**
         * 币种总权益
         */
        @JSONField(name = "eq")
        @JsonProperty("eq")
        private BigDecimal eq;

        /**
         * 币种余额
         */
        @JSONField(name = "cashBal")
        @JsonProperty("cashBal")
        private BigDecimal cashBal;

        /**
         * 币种余额信息的更新时间，Unix时间戳的毫秒数格式，如 1597026383085
         */
        @JSONField(name = "uTime")
        @JsonProperty("uTime")
        private Long uTime;

        /**
         * 币种逐仓仓位权益
         * 适用于单币种保证金模式/跨币种保证金模式/组合保证金模式
         */
        @JSONField(name = "isoEq")
        @JsonProperty("isoEq")
        private BigDecimal isoEq;

        /**
         * 可用保证金
         * 适用于单币种保证金模式/跨币种保证金模式/组合保证金模式
         */
        @JSONField(name = "availEq")
        @JsonProperty("availEq")
        private BigDecimal availEq;

        /**
         * 美金层面币种折算权益
         */
        @JSONField(name = "disEq")
        @JsonProperty("disEq")
        private BigDecimal disEq;

        /**
         * 抄底宝、逃顶宝功能的币种冻结金额
         */
        @JSONField(name = "fixedBal")
        @JsonProperty("fixedBal")
        private BigDecimal fixedBal;

        /**
         * 可用余额
         */
        @JSONField(name = "availBal")
        @JsonProperty("availBal")
        private BigDecimal availBal;

        /**
         * 币种占用金额
         */
        @JSONField(name = "frozenBal")
        @JsonProperty("frozenBal")
        private BigDecimal frozenBal;

        /**
         * 挂单冻结数量
         * 适用于简单交易模式/单币种保证金模式/跨币种保证金模式
         */
        @JSONField(name = "ordFrozen")
        @JsonProperty("ordFrozen")
        private BigDecimal ordFrozen;

        /**
         * 币种负债额
         * 值为正数，如 21625.64
         * 适用于跨币种保证金模式/组合保证金模式
         */
        @JSONField(name = "liab")
        @JsonProperty("liab")
        private BigDecimal liab;

        /**
         * 未实现盈亏
         * 适用于单币种保证金模式/跨币种保证金模式/组合保证金模式
         */
        @JSONField(name = "upl")
        @JsonProperty("upl")
        private BigDecimal upl;

        /**
         * 由于仓位未实现亏损导致的负债
         * 适用于跨币种保证金模式/组合保证金模式
         */
        @JSONField(name = "uplLiab")
        @JsonProperty("uplLiab")
        private BigDecimal uplLiab;

        /**
         * 币种全仓负债额
         * 适用于跨币种保证金模式/组合保证金模式
         */
        @JSONField(name = "crossLiab")
        @JsonProperty("crossLiab")
        private BigDecimal crossLiab;

        /**
         * 币种逐仓负债额
         * 适用于跨币种保证金模式/组合保证金模式
         */
        @JSONField(name = "isoLiab")
        @JsonProperty("isoLiab")
        private BigDecimal isoLiab;

        /**
         * 体验金余额
         */
        @JSONField(name = "rewardBal")
        @JsonProperty("rewardBal")
        private BigDecimal rewardBal;

        /**
         * 保证金率，衡量账户内某项资产风险的指标
         * 适用于单币种保证金模式
         */
        @JSONField(name = "mgnRatio")
        @JsonProperty("mgnRatio")
        private BigDecimal mgnRatio;

        /**
         * 计息，应扣未扣利息
         * 值为正数，如 9.01
         * 适用于跨币种保证金模式/组合保证金模式
         */
        @JSONField(name = "interest")
        @JsonProperty("interest")
        private BigDecimal interest;

        /**
         * 当前负债币种触发系统自动换币的风险
         * 0、1、2、3、4、5 其中之一，数字越大代表您的负债币种触发自动换币概率越高
         * 适用于跨币种保证金模式/组合保证金模式
         */
        @JSONField(name = "twap")
        @JsonProperty("twap")
        private Integer twap;

        /**
         * 币种最大可借
         * 适用于跨币种保证金模式/组合保证金模式 的全仓
         */
        @JSONField(name = "maxLoan")
        @JsonProperty("maxLoan")
        private BigDecimal maxLoan;

        /**
         * 币种权益美金价值
         */
        @JSONField(name = "eqUsd")
        @JsonProperty("eqUsd")
        private BigDecimal eqUsd;

        /**
         * 币种美金层面潜在借币占用保证金
         * 仅适用于跨币种保证金模式/组合保证金模式。在其他账户模式下为""。
         */
        @JSONField(name = "borrowFroz")
        @JsonProperty("borrowFroz")
        private BigDecimal borrowFroz;

        /**
         * 币种杠杆倍数
         * 适用于单币种保证金模式
         */
        @JSONField(name = "notionalLever")
        @JsonProperty("notionalLever")
        private BigDecimal notionalLever;

        /**
         * 策略权益
         */
        @JSONField(name = "stgyEq")
        @JsonProperty("stgyEq")
        private BigDecimal stgyEq;

        /**
         * 逐仓未实现盈亏
         * 适用于单币种保证金模式/跨币种保证金模式/组合保证金模式
         */
        @JSONField(name = "isoUpl")
        @JsonProperty("isoUpl")
        private BigDecimal isoUpl;

        /**
         * 现货对冲占用数量
         * 适用于组合保证金模式
         */
        @JSONField(name = "spotInUseAmt")
        @JsonProperty("spotInUseAmt")
        private BigDecimal spotInUseAmt;

        /**
         * 现货逐仓余额
         * 仅适用于现货带单/跟单
         * 适用于简单交易模式/单币种保证金模式
         */
        @JSONField(name = "spotIsoBal")
        @JsonProperty("spotIsoBal")
        private BigDecimal spotIsoBal;

        /**
         * 币种维度占用保证金
         * 适用于单币种保证金模式
         */
        @JSONField(name = "imr")
        @JsonProperty("imr")
        private BigDecimal imr;

        /**
         * 币种维度维持保证金
         * 适用于单币种保证金模式
         */
        @JSONField(name = "mmr")
        @JsonProperty("mmr")
        private BigDecimal mmr;

    }

}
