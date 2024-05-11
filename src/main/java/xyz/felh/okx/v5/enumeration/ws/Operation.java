package xyz.felh.okx.v5.enumeration.ws;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Operation {

    LOGIN("login", null),
    SUBSCRIBE("subscribe", null),
    UNSUBSCRIBE("unsubscribe", null),
    // once operation
    ORDER("order", "onPlaceOrderResponse"),
    BATCH_ORDERS("batch-orders", "onBatchPlaceOrdersResponse"),
    CANCEL_ORDER("cancel-order", "onCancelOrderResponse"),
    BATCH_CANCEL_ORDERS("batch-cancel-orders", "onBatchCancelOrdersResponse"),
    AMEND_ORDER("amend-order", "onAmendOrderResponse"),
    BATCH_AMEND_ORDERS("batch-amend-orders", "onBatchAmendOrdersResponse"),
    MASS_CANCEL_ORDERS("mass-cancel", "onMassCancelOrdersResponse"),
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
