package xyz.felh.okx.v5.entity.ws.request;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Operation {

    LOGIN("login", null),
    SUBSCRIBE("subscribe", null),
    UNSUBSCRIBE("unsubscribe", null),
    ORDER("order", "onPlaceOrderResponse"),
    ;

    private final String value;
    private final String rspCallbackMethodName;

    Operation(final String value, final String rspCallbackMethodName) {
        this.value = value;
        this.rspCallbackMethodName = rspCallbackMethodName;
    }

    @JsonValue
    public String value() {
        return value;
    }

    public static Operation fromValue(final String value) {
        for (Operation operation : Operation.values()) {
            if (operation.value.equals(value)) {
                return operation;
            }
        }
        return null;
    }

}
