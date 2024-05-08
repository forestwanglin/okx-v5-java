package xyz.felh.okx.v5.enumeration.ws;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum QuickMgnType {

    /**
     * 手动
     */
    MANUAL("manual"),
    /**
     * 自动借币
     */
    AUTO_BORROW("auto_borrow"),
    /**
     * 自动还币
     */
    AUTO_REPAY("auto_repay"),;

    private final String value;

    QuickMgnType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }


}
