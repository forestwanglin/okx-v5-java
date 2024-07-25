package xyz.felh.okx.v5.entity.ws.response.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PlaceOrderArg implements WsResponseArg {

    /**
     * Order ID
     */
    @JSONField(name = "ordId")
    @JsonProperty("ordId")
    private String ordId;

    /**
     * Client Order ID as assigned by the client
     */
    @JSONField(name = "clOrdId")
    @JsonProperty("clOrdId")
    private String clOrdId;

    /**
     * Order tag
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;

    /**
     * Timestamp when the order request processing is finished by our system, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "ts")
    @JsonProperty("ts")
    private Long ts;

    /**
     * Order status code, 0 means success
     */
    @JSONField(name = "sCode")
    @JsonProperty("sCode")
    private Integer sCode;

    /**
     * Rejection or success message of event execution.
     */
    @JSONField(name = "sMsg")
    @JsonProperty("sMsg")
    private String sMsg;

}
