package xyz.felh.okx.v5.enumeration.ws;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum FundingRateSettleState {

    /**
     * 结算中
     */
    PROCESSING("processing"),
    /**
     * 已结算
     */
    SETTLED("settled");

    private final String value;

    FundingRateSettleState(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }


}
