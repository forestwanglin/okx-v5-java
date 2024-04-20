package xyz.felh.okx.v5.entity.ws.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.enumeration.InstrumentType;
import xyz.felh.okx.v5.enumeration.MgnMode;
import xyz.felh.okx.v5.enumeration.PositionsSide;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class LiquidationWarning implements WsSubscribeEntity {

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
     * 持仓数量
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
     * 标记价格
     */
    @JSONField(name = "markPx")
    @JsonProperty("markPx")
    private BigDecimal markPx;

    /**
     * 保证金率
     */
    @JSONField(name = "mgnRatio")
    @JsonProperty("mgnRatio")
    private BigDecimal mgnRatio;

    /**
     * 占用保证金的币种
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

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

}
