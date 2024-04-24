package xyz.felh.okx.v5.entity.ws.response.pub;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;
import xyz.felh.okx.v5.enumeration.Channel;
import xyz.felh.okx.v5.enumeration.InstrumentType;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class LiquidationOrdersArg extends WsResponseArg {

    @Builder.Default
    private Channel channel = Channel.LIQUIDATION_ORDERS;

    /**
     * 产品type
     */
    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

}

