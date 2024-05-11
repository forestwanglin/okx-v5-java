package xyz.felh.okx.v5.entity.ws.response.biz;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.response.WsChannelResponseArg;
import xyz.felh.okx.v5.enumeration.ws.Channel;
import xyz.felh.okx.v5.enumeration.ws.InstrumentType;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class GridOrderSpotArg implements WsChannelResponseArg {

    @Builder.Default
    @JSONField(name = "channel")
    @JsonProperty("channel")
    private Channel channel = Channel.GRID_ORDERS_SPOT;

    /**
     * Instrument type
     * SPOT
     * ANY
     */
    @NonNull
    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

    /**
     * 用户标识
     */
    @JSONField(name = "uid")
    @JsonProperty("uid")
    private String uid;

}
