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
public class EstimatedPrice implements WsSubscribeEntity {

    /**
     * 产品类型
     */
    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

    /**
     * 产品ID，如 BTC-USD-170310
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * 预估交割/行权价
     */
    @JSONField(name = "settlePx")
    @JsonProperty("settlePx")
    private BigDecimal settlePx;

    /**
     * 数据更新时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "ts")
    @JsonProperty("ts")
    private Long ts;

}
