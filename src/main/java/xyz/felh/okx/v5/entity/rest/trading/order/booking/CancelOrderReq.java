package xyz.felh.okx.v5.entity.rest.trading.order.booking;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.rest.IOkxRestReq;
import xyz.felh.okx.v5.enumeration.OrderType;
import xyz.felh.okx.v5.enumeration.PositionsSide;
import xyz.felh.okx.v5.enumeration.Side;
import xyz.felh.okx.v5.enumeration.TriggerPxType;
import xyz.felh.okx.v5.enumeration.rest.StpMode;
import xyz.felh.okx.v5.enumeration.rest.TpOrderKind;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderReq implements IOkxRestReq {

    /**
     * 产品ID，如 BTC-USDT
     */
    @NonNull
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * 订单ID， ordId和clOrdId必须传一个，若传两个，以ordId为主
     */
    @JSONField(name = "ordId")
    @JsonProperty("ordId")
    private String ordId;

    /**
     * 用户自定义ID
     */
    @JSONField(name = "clOrdId")
    @JsonProperty("clOrdId")
    private String clOrdId;

}
