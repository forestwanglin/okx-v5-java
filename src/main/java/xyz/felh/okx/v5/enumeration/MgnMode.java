package xyz.felh.okx.v5.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MgnMode {

    /**
     * 全仓
     */
    CROSS("cross"),
    /**
     * 逐仓
     */
    ISOLATED("isolated");

    private final String value;

    MgnMode(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }


}
