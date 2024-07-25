package xyz.felh.okx.v5.entity.ws.pub;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.enumeration.PositionsSide;
import xyz.felh.okx.v5.enumeration.Side;
import xyz.felh.okx.v5.enumeration.InstrumentType;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class LiquidationOrders implements WsSubscribeEntity {

    /**
     * 产品类型
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
     * 标的指数，仅适用于交割/永续/期权
     */
    @JSONField(name = "uly")
    @JsonProperty("uly")
    private String uly;

    /**
     * 详细内容
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
         * 订单方向
         * buy：买
         * sell：卖，仅适用于交割/永续
         */
        @JSONField(name = "side")
        @JsonProperty("side")
        private Side side;

        /**
         * 持仓方向
         * long：多
         * short：空
         * side和posSide组合方式，sell/long：强平多 ; buy/short:强平空，仅适用于交割/永续
         */
        @JSONField(name = "posSide")
        @JsonProperty("posSide")
        private PositionsSide posSide;

        /**
         * 破产价格，与系统爆仓账号委托成交的价格，仅适用于交割/永续
         */
        @JSONField(name = "bkPx")
        @JsonProperty("bkPx")
        private BigDecimal bkPx;

        /**
         * 强平数量，仅适用于杠杆/交割/永续
         */
        @JSONField(name = "sz")
        @JsonProperty("sz")
        private BigDecimal sz;

        /**
         * 穿仓亏损数量
         */
        @JSONField(name = "bkLoss")
        @JsonProperty("bkLoss")
        private BigDecimal bkLoss;

        /**
         * 强平币种，仅适用于币币杠杆
         */
        @JSONField(name = "ccy")
        @JsonProperty("ccy")
        private String ccy;

        /**
         * 强平发生的时间，Unix时间戳的毫秒数格式，如 1597026383085 /
         */
        @JSONField(name = "ts")
        @JsonProperty("ts")
        private Long ts;

    }

}
