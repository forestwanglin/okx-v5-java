package xyz.felh.okx.v5.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum OrderCategory {

    /**
     * 普通委托订单种类
     */
    NORMAL("normal"),
    /**
     * TWAP订单种类
     */
    TWAP("twap"),
    /**
     * ADL订单种类
     */
    ADL("adl"),
    /**
     * 爆仓订单种类
     */
    FULL_LIQUIDATION("full_liquidation"),
    /**
     * 减仓订单种类
     */
    PARTIAL_LIQUIDATION("partial_liquidation"),
    /**
     * 交割
     */
    DELIVERY("delivery"),
    /**
     * 对冲减仓类型订单
     */
    DDH("ddh");


    private final String value;

    OrderCategory(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

}
