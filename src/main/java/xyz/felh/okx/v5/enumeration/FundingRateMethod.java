package xyz.felh.okx.v5.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum FundingRateMethod {

    /**
     * 当期收
     */
    CURRENT_PERIOD("current_period"),
    /**
     * 跨期收
     */
    NEXT_PERIOD("next_period");

    private final String value;

    FundingRateMethod(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }


}
