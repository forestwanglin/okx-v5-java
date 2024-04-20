package xyz.felh.okx.v5.enumeration;

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
    OPEN_INTEREST("open-interest"),


    //****************** 私有频道，无需验证即可使用 *************************/
    /**
     * 获取账户信息，首次订阅按照订阅维度推送数据，此外，当下单、撤单、成交等事件触发时，推送数据以及按照订阅维度定时推送数据
     * 系统将限制订阅 WebSocket 频道的最大并发连接数。
     * <p>
     * 1、如果同时成交多个订单，那么将对账户频道的推送尽量进行聚合。
     * <p>
     * 2、首次推送，只推用户币种层面资产不为0的账户信息。币种层面资产不为0的定义：eq、availEq、availBal 中任意一个字段不为0，即币种层面资产不为0。如果数据太大无法在单个推送消息中发送，它将被分成多个消息发送。
     * <p>
     * 3、定时推送，只推用户币种层面资产不为0的账户信息。币种层面资产不为0的定义：eq、availEq、availBal 中任意一个字段不为0，即币种层面资产不为0。
     * <p>
     * 例：按照所有币种订阅且有5个币种的余额或者权益都不为0，首次和定时推全部5个；账户下有一个币种余额或者权益改变，那么账户变更的触发只推这一个。
     * <p>
     * 币种余额小于 1e-8 的币种信息，会在 details 中过滤掉不返回。
     */
    ACCOUNT("account"),

    /**
     * 获取持仓信息，首次订阅按照订阅维度推送数据，此外，当下单、撤单等事件触发时，推送数据以及按照订阅维度定时推送数据
     * 系统将限制订阅 WebSocket 频道的最大并发连接数。
     */
    POSITIONS("positions"),

    /**
     * 获取账户余额和持仓信息，首次订阅按照订阅维度推送数据，此外，当成交、资金划转等事件触发时，推送数据。
     * <p>
     * 该频道适用于尽快获取账户现金余额和仓位资产变化的信息。
     * 系统将限制订阅 WebSocket 频道的最大并发连接数。详情在限制 WebSocket 私有频道对应连接数量
     */
    BALANCE_AND_POSITION("balance_and_position"),

    /**
     * 此推送频道仅作为风险提示，不建议作为策略交易的风险判断。
     * 在行情剧烈波动的情况下，可能会出现此消息推送的同时仓位已经被强平的可能性。
     * 预警会在某一个逐仓仓位有风险时推送。预警会在所有全仓仓位有风险时推送。
     * 系统将限制订阅 WebSocket 频道的最大并发连接数。详情在限制 WebSocket 私有频道对应连接数量
     */
    LIQUIDATION_WARNING("liquidation-warning"),

    /**
     * 获取账户资产的greeks信息，首次订阅按照订阅维度推送数据，此外，当增加或者减少币种余额、持仓数量等事件触发时，推送数据以及按照订阅维度定时推送数据
     * 系统将限制订阅 WebSocket 频道的最大并发连接数。详情在限制 WebSocket 私有频道对应连接数量
     */
    ACCOUNT_GREEKS("account-greeks"),
    ;

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
