package xyz.felh.okx.v5.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum OrderType {

    /**
     * 市价单
     */
    MARKET("market"),
    /**
     * 限价单
     */
    LIMIT("limit"),
    /**
     * 只做maker单
     */
    POST_ONLY("post_only"),
    /**
     * 全部成交或立即取消
     */
    FOK("fok"),
    /**
     * 立即成交并取消剩余
     */
    IOC("ioc"),
    /**
     * 市价委托立即成交并取消剩余（仅适用交割、永续）
     */
    OPTIMAL_LIMIT_IOC("optimal_limit_ioc"),
    /**
     * 做市商保护(仅适用于组合保证金账户模式下的期权订单)
     */
    MMP("mmp"),
    /**
     * 做市商保护且只做maker单(仅适用于组合保证金账户模式下的期权订单)
     */
    MMP_AND_POST_ONLY("mmp_and_post_only");


    private final String value;

    OrderType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

}
