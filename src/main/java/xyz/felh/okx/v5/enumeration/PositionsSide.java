package xyz.felh.okx.v5.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum PositionsSide {

    /**
     * 开平仓模式开多
     */
    LONG("long"),
    /**
     * 开平仓模式开空
     */
    SHORT("short"),
    /**
     * 买卖模式（交割/永续/期权：pos为正代表开多，pos为负代表开空。币币杠杆：posCcy为交易货币时，代表开多；posCcy为计价货币时，代表开空。）
     */
    NET("net");

    private final String value;

    PositionsSide(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

}
