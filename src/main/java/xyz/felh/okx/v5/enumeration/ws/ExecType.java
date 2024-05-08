package xyz.felh.okx.v5.enumeration.ws;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ExecType {

    /**
     * taker
     */
    T("T"),
    /**
     * maker
     */
    M("M");

    private final String value;

    ExecType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }


}
