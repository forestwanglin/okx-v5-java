package xyz.felh.okx.v5.entity.ws;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


@Getter
public enum Channel {

    //****************** 公共频道，无需验证即可使用 *************************/
    /**
     * 当有产品状态变化时（如期货交割、期权行权、新合约/币对上线、人工暂停/恢复交易等），推送产品的增量数据。
     * (2022年12月28日起不再推送全量数据，点此<a href="https://www.okx.com/docs-v5/log_zh/#2022-12-06">查看详情</a>)；
     */
    INSTRUMENTS("instruments"),
    /**
     * 获取持仓总量，每3s有数据更新推送一次数据
     */
    OPEN_INTEREST("open-interest");

    private final String value;

    Channel(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

    public static Channel fromValue(final String value) {
        for (Channel channel : Channel.values()) {
            if (channel.value.equals(value)) {
                return channel;
            }
        }
        return null;
    }

}
