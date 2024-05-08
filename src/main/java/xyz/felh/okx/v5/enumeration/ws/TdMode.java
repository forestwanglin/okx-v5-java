package xyz.felh.okx.v5.enumeration.ws;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TdMode {

    // 保证金模式
    /**
     * 逐仓
     */
    ISOLATED("isolated"),
    /**
     * 全仓
     */
    CROSS("cross"),

    // 非保证金模式
    /**
     * 现金
     */
    CASH("cash"),
    /**
     * 现货逐仓(仅适用于现货带单) ，现货带单时，tdMode 的值需要指定为spot_isolated
     */
    SPOT_ISOLATED("spot_isolated");

    private final String value;

    TdMode(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }


}
