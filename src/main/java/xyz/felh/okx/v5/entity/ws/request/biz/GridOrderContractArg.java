package xyz.felh.okx.v5.entity.ws.request.biz;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.request.WsChannelRequestArg;
import xyz.felh.okx.v5.enumeration.ws.Channel;
import xyz.felh.okx.v5.enumeration.ws.InstrumentType;


@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class GridOrderContractArg implements WsChannelRequestArg {

    @Builder.Default
    @JSONField(name = "channel")
    @JsonProperty("channel")
    private Channel channel = Channel.GRID_ORDERS_CONTRACT;

    /**
     * Instrument type
     * SWAP
     * FUTURES
     * ANY
     */
    @NonNull
    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

    /**
     * Instrument ID
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * Algo Order ID
     */
    @JSONField(name = "algoId")
    @JsonProperty("algoId")
    private String algoId;
}