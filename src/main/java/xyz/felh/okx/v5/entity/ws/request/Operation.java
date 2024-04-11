package xyz.felh.okx.v5.entity.ws.request;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Operation {

    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe");

    private final String value;

    Operation(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

}
