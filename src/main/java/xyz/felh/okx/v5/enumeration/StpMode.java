package xyz.felh.okx.v5.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StpMode {

    CANCEL_MAKER("cancel_maker"),
    CANCEL_TAKER("cancel_taker"),
    CANCEL_BOTH("cancel_both");

    private final String value;

    StpMode(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

}
