package xyz.felh.okx.v5.enumeration.ws;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AdlWarningState {

    /**
     * 普通状态
     */
    MARKET("normal"),
    /**
     * 预警状态
     */
    LIMIT("warning"),

    /**
     * 已开启自动减仓
     */
    ADL("adl");


    private final String value;

    AdlWarningState(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

}
