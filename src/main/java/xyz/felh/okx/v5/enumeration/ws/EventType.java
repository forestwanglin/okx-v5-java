package xyz.felh.okx.v5.enumeration.ws;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum EventType {

    /**
     * 首推快照
     */
    SNAPSHOT("snapshot"),
    /**
     * 交割
     */
    DELIVERED("delivered"),
    /**
     * 行权
     */
    EXERCISED("exercised"),
    /**
     * 划转
     */
    TRANSFERRED("transferred"),
    /**
     * 成交
     */
    FILLED("filled"),
    /**
     * 强平
     */
    LIQUIDATION("liquidation"),
    /**
     * 穿仓补偿
     */
    CLAW_BACK("claw_back"),
    /**
     * ADL自动减仓
     */
    ADL("adl"),
    /**
     * 资金费
     */
    FUNDING_FEE("funding_fee"),
    /**
     * 调整保证金
     */
    ADJUST_MARGIN("adjust_margin"),
    /**
     * 设置杠杆
     */
    ST_LEVERAGE("set_leverage"),
    /**
     * 扣息
     */
    INTEREST_DEDUCTION("interest_deduction"),
    ;


    private final String value;

    EventType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }


}
