package xyz.felh.okx.v5.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Side {

    /**
     * 买
     */
    BUY("buy"),
    /**
     * 卖
     */
    SELL("sell");

    private final String value;

    Side(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

}
