package xyz.felh.okx.v5.entity.ws.response;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Event {

    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe"),
    ERROR("error");

    private final String value;

    Event(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

}
