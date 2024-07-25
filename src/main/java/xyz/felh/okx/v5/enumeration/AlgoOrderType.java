package xyz.felh.okx.v5.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AlgoOrderType {

    /**
     * Spot grid
     */
    GRID("grid"),
    /**
     * Contract grid
     */
    CONTRACT_GRID("contract_grid");

    private final String value;

    AlgoOrderType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }


}
