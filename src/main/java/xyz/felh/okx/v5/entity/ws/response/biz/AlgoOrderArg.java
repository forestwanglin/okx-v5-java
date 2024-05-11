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
public class AlgoOrderArg implements WsChannelResponseArg {

    @Builder.Default
    @JSONField(name = "channel")
    @JsonProperty("channel")
    private Channel channel = Channel.ALGO_ORDERS;

    /**
     * Instrument type
     * SPOT
     * MARGIN
     * SWAP
     * FUTURES
     * ANY
     */
    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

    /**
     * Instrument family
     * Applicable to FUTURES/SWAP/OPTION
     */
    @JSONField(name = "instFamily")
    @JsonProperty("instFamily")
    private String instFamily;

    /**
     * Instrument ID
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * User Identifier
     */
    @JSONField(name = "uid")
    @JsonProperty("uid")
    private String uid;

}
