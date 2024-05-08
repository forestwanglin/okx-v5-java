package xyz.felh.okx.v5.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum OrderState {

    /**
     * 等待成交
     */
    LIVE("live"),
    /**
     * 部分成交
     */
    PARTIALLY_FILLED("partially_filled"),
    /**
     * 完全成交
     */
    FILLED("filled"),
    /**
     * 撤单成功
     */
    CANCELED("canceled"),
    /**
     * 做市商保护机制导致的自动撤单
     */
    MP_CANCELED("mmp_canceled");


    private final String value;

    OrderState(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

}
