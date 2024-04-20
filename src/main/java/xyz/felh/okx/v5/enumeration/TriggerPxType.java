package xyz.felh.okx.v5.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TriggerPxType {

    /**
     * 最新价格
     */
    LAST("last"),
    /**
     * 指数价格
     */
    INDEX("index"),
    /**
     * 标记价格
     */
    MARK("mark");

    private final String value;

    TriggerPxType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }


}
