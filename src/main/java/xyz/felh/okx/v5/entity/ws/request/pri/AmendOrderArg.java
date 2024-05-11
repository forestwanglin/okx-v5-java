package xyz.felh.okx.v5.entity.ws.request.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AmendOrderArg implements WsRequestArg {

    /**
     * Instrument ID, e.g. BTC-USDT
     */
    @NonNull
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * Whether the order needs to be automatically canceled when the order amendment fails
     * Valid options: false or true, the default is false.
     */
    @JSONField(name = "cxlOnFail")
    @JsonProperty("cxlOnFail")
    private Boolean cxlOnFail;

    /**
     * Order ID
     * Either ordId or clOrdId is required, if both are passed, ordId will be used.
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
     * Client Request ID as assigned by the client for order amendment
     * A combination of case-sensitive alphanumerics, all numbers, or all letters of up to 32 characters.
     */
    @JSONField(name = "reqId")
    @JsonProperty("reqId")
    private String reqId;

    /**
     * New quantity after amendment. Either newSz or newPx is required. When amending a partially-filled order, the newSz should include the amount that has been filled.
     */
    @JSONField(name = "newSz")
    @JsonProperty("newSz")
    private BigDecimal newSz;

    /**
     * New price after amendment.
     * When modifying options orders, users can only fill in one of the following: newPx, newPxUsd, or newPxVol. It must be consistent with parameters when placing orders. For example, if users placed the order using px, they should use newPx when modifying the order.
     */
    @JSONField(name = "newPx")
    @JsonProperty("newPx")
    private BigDecimal newPx;

    /**
     * Modify options orders using USD prices
     * Only applicable to options.
     * When modifying options orders, users can only fill in one of the following: newPx, newPxUsd, or newPxVol.
     */
    @JSONField(name = "newPxUsd")
    @JsonProperty("newPxUsd")
    private BigDecimal newPxUsd;

    /**
     * Modify options orders based on implied volatility, where 1 represents 100%
     * Only applicable to options.
     * When modifying options orders, users can only fill in one of the following: newPx, newPxUsd, or newPxVol.
     */
    @JSONField(name = "newPxVol")
    @JsonProperty("newPxVol")
    private BigDecimal newPxVol;

}
