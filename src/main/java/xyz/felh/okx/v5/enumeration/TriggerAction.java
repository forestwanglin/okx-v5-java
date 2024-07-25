package xyz.felh.okx.v5.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TriggerAction {

    /**
     * START
     */
    START("start"),
    /**
     * STOP
     */
    STOP("stop");

    private final String value;

    TriggerAction(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }


}
