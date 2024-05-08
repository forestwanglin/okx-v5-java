package xyz.felh.okx.v5.entity.ws.biz;

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
public class IndexCandlesticks implements WsSubscribeEntity {

    /**
     * 开始时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "ts")
    @JsonProperty("ts")
    private Long ts;

    /**
     * 开盘价格
     */
    @JSONField(name = "o")
    @JsonProperty("o")
    private BigDecimal o;

    /**
     * 最高价格
     */
    @JSONField(name = "h")
    @JsonProperty("h")
    private BigDecimal h;

    /**
     * 最低价格
     */
    @JSONField(name = "l")
    @JsonProperty("l")
    private BigDecimal l;

    /**
     * 收盘价格
     */
    @JSONField(name = "c")
    @JsonProperty("c")
    private BigDecimal c;

    /**
     * K线状态
     */
    @JSONField(name = "confirm")
    @JsonProperty("confirm")
    private Boolean confirm;

}
