package xyz.felh.okx.v5.entity.ws.pub;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.enumeration.ws.InstrumentType;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PriceLimit implements WsSubscribeEntity {

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
     * 最高买价
     * 当enabled为false时，返回""
     */
    @JSONField(name = "buyLmt")
    @JsonProperty("buyLmt")
    private BigDecimal buyLmt;

    /**
     * 最低卖价
     * 当enabled为false时，返回""
     */
    @JSONField(name = "sellLmt")
    @JsonProperty("sellLmt")
    private BigDecimal sellLmt;

    /**
     * 限价数据更新时间 ，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "ts")
    @JsonProperty("ts")
    private Long ts;

    /**
     * 限价是否生效
     * true：限价生效
     * false：限价不生效
     */
    @JSONField(name = "enabled")
    @JsonProperty("enabled")
    private Boolean enabled;

}
