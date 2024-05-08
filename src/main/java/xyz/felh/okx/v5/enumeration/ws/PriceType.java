package xyz.felh.okx.v5.enumeration.ws;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum PriceType {

    /**
     * 代表按价格下单，单位为币 (请求参数 px 的数值单位是BTC或ETH)
     */
    PX("px"),
    /**
     * 代表按pxVol下单
     */
    PX_VOL("pxVol"),
    /**
     * 代表按照pxUsd下单，单位为USD (请求参数px 的数值单位是USD)
     */
    PX_USD("pxUsd");

    private final String value;

    PriceType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }


}
