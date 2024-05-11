package xyz.felh.okx.v5.entity.ws.request.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CancelOrderArg implements WsRequestArg {

    /**
     * Instrument ID, e.g. BTC-USDT
     */
    @NonNull
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * Client Order ID as assigned by the client
     * A combination of case-sensitive alphanumerics, all numbers, or all letters of up to 32 characters.
     */
    @JSONField(name = "clOrdId")
    @JsonProperty("clOrdId")
    private String clOrdId;

    /**
     * Order ID
     * Either ordId or clOrdId is required, if both are passed, ordId will be used
     */
    @JSONField(name = "ordId")
    @JsonProperty("ordId")
    private String ordId;

}
