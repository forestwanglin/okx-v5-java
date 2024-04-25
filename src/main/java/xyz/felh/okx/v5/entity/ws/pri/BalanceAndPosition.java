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
import xyz.felh.okx.v5.enumeration.ws.EventType;
import xyz.felh.okx.v5.enumeration.ws.InstrumentType;
import xyz.felh.okx.v5.enumeration.ws.MgnMode;
import xyz.felh.okx.v5.enumeration.PositionsSide;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BalanceAndPosition implements WsSubscribeEntity {

    /**
     * 推送时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "pTime")
    @JsonProperty("pTime")
    private Long pTime;

    /**
     * 事件类型
     */
    @JSONField(name = "eventType")
    @JsonProperty("eventType")
    private EventType eventType;

    /**
     * 余额数据
     */
    @JSONField(name = "balData")
    @JsonProperty("balData")
    private List<BalanceData> balData;

    /**
     * 持仓数据
     */
    @JSONField(name = "posData")
    @JsonProperty("posData")
    private List<PositionData> posData;

    /**
     * 成交数据
     */
    @JSONField(name = "trades")
    @JsonProperty("trades")
    private List<Trade> trades;


    @Data
    public static class BalanceData implements IOkxWsObject {

        /**
         * 币种
         */
        @JSONField(name = "ccy")
        @JsonProperty("ccy")
        private String ccy;

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
    }


    @Data
    public static class PositionData implements IOkxWsObject {

        /**
         * 持仓ID
         */
        @JSONField(name = "posId")
        @JsonProperty("posId")
        private String posId;

        /**
         * 最新成交ID
         */
        @JSONField(name = "tradeId")
        @JsonProperty("tradeId")
        private String tradeId;

        /**
         * 交易产品ID，如 BTC-USD-180213
         */
        @JSONField(name = "instId")
        @JsonProperty("instId")
        private String instId;

        /**
         * 交易产品类型
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
         * 开仓平均价
         */
        @JSONField(name = "avgPx")
        @JsonProperty("avgPx")
        private BigDecimal avgPx;

        /**
         * 占用保证金的币种
         */
        @JSONField(name = "ccy")
        @JsonProperty("ccy")
        private String ccy;

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
         * 持仓数量币种
         * 只适用于币币杠杆仓位。当是交割、永续、期权持仓时，该字段返回“”
         */
        @JSONField(name = "posCcy")
        @JsonProperty("posCcy")
        private String posCcy;

        /**
         * 仓位信息的更新时间，Unix时间戳的毫秒数格式，如 1597026383085
         */
        @JSONField(name = "uTime")
        @JsonProperty("uTime")
        private Long uTime;

    }

    @Data
    public static class Trade implements IOkxWsObject {
        /**
         * 产品ID，如 BTC-USDT
         */
        @JSONField(name = "instId")
        @JsonProperty("instId")
        private String instId;

        /**
         * 最新成交ID
         */
        @JSONField(name = "tradeId")
        @JsonProperty("tradeId")
        private String tradeId;
    }

}
