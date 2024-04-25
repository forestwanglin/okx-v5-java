package xyz.felh.okx.v5.entity.ws.pub;

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
public class IndexTickers implements WsSubscribeEntity {

    /**
     * 指数，以USD、USDT、BTC 为计价货币的指数，如 BTC-USDT
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * 最新指数价格
     */
    @JSONField(name = "idxPx")
    @JsonProperty("idxPx")
    private BigDecimal idxPx;

    /**
     * 24小时开盘价
     */
    @JSONField(name = "open24h")
    @JsonProperty("open24h")
    private BigDecimal open24h;

    /**
     * 24小时指数最高价格
     */
    @JSONField(name = "high24h")
    @JsonProperty("high24h")
    private BigDecimal high24h;

    /**
     * 24小时指数最低价格
     */
    @JSONField(name = "low24h")
    @JsonProperty("low24h")
    private BigDecimal low24h;

    /**
     * UTC 0 时开盘价
     */
    @JSONField(name = "sodUtc0")
    @JsonProperty("sodUtc0")
    private BigDecimal sodUtc0;

    /**
     * UTC+8 时开盘价
     */
    @JSONField(name = "sodUtc8")
    @JsonProperty("sodUtc8")
    private BigDecimal sodUtc8;

    /**
     * 指数价格更新时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "ts")
    @JsonProperty("ts")
    private Long ts;


}
