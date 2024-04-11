package xyz.felh.okx.v5.entity.ws;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


@Getter
public enum InstrumentType {

    /**
     * 币币
     */
    SPOT("SPOT"),
    /**
     * 币币杠杆
     */
    MARGIN("MARGIN"),
    /**
     * 永续合约
     */
    SWAP("SWAP"),
    /**
     * 交割合约
     */
    FUTURES("FUTURES"),
    /**
     * 期权
     */
    OPTION("OPTION");

    private final String value;

    InstrumentType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

}
