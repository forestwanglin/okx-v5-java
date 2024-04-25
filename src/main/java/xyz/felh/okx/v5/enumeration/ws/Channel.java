package xyz.felh.okx.v5.enumeration.ws;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


@Getter
public enum Channel {

    //****************** 公共频道，无需验证即可使用 *************************/
    /**
     * 当有产品状态变化时（如期货交割、期权行权、新合约/币对上线、人工暂停/恢复交易等），推送产品的增量数据。
     * (2022年12月28日起不再推送全量数据，点此<a href="https://www.okx.com/docs-v5/log_zh/#2022-12-06">查看详情</a>)；
     */
    INSTRUMENTS("instruments", "onOperateInstruments", "onReceiveInstruments"),
    /**
     * 获取持仓总量，每3s有数据更新推送一次数据
     */
    OPEN_INTEREST("open-interest", "onOperateOpenInterest", "onReceiveOpenInterest"),

    /**
     * 资金费率频道
     * 获取永续合约资金费率，30秒到90秒内推送一次数据
     */
    FUNDING_RATE("funding-rate", "onOperateFundingRate", "onReceiveFundingRate"),

    /**
     * 限价频道
     * 获取交易产品的最高买价和最低卖价。限价有变化时，每秒推送一次数据，限价没变化时，不推送数据
     */
    PRICE_LIMIT("price-limit", "onOperatePriceLimit", "onReceivePriceLimit"),

    /**
     * 期权定价频道
     * 获取所有期权合约详细定价信息，一次性推送所有
     */
    OPT_SUMMARY("opt-summary", "onOperateOptSummary", "onReceiveOptSummary"),

    /**
     * 预估交割/行权价格频道
     * 获取永续合约，交割合约和期权预估交割/行权价。交割/行权预估价只有交割/行权前一小时开始推送预估交割/行权价，有价格变化就推送
     */
    ESTIMATED_PRICE("estimated-price", "onOperateEstimatedPrice", "onReceiveEstimatedPrice"),

    /**
     * 标记价格频道
     * 获取标记价格，标记价格有变化时，每200ms推送一次数据，标记价格没变化时，每10s推送一次数据
     */
    MARK_PRICE("mark-price", "onOperateMarkPrice", "onReceiveMarkPrice"),

    /**
     * 指数行情频道
     * 获取指数的行情数据。每100ms有变化就推送一次数据，否则一分钟推一次。
     */
    INDEX_TICKERS("index-tickers", "onOperateIndexTickers", "onReceiveIndexTickers"),

    /**
     * 平台公共爆仓单频道
     * 获取爆仓单信息。产品ID维度最多一秒推一条爆仓单信息。
     */
    LIQUIDATION_ORDERS("liquidation-orders", "onOperateLiquidationOrders", "onReceiveLiquidationOrders"),

    /**
     * 自动减仓预警频道
     * 普通状态（normal）下，每分钟推送一次，展示风险准备金的余额等信息。
     * 预警状态下或有自动减仓风险（warning/adl）时，每1秒推送一次数据，展示风险准备金的实时下降率等信息。
     * 更多自动减仓细节，请见自动减仓机制介绍
     */
    ADL_WARNINGS("adl-warnings", "onOperateAdlWarning", "onReceiveAdlWarning"),

    //****************** 私有频道，无需验证即可使用 *************************/
    /**
     * 获取账户信息，首次订阅按照订阅维度推送数据，此外，当下单、撤单、成交等事件触发时，推送数据以及按照订阅维度定时推送数据
     * 系统将限制订阅 WebSocket 频道的最大并发连接数。
     * 1、如果同时成交多个订单，那么将对账户频道的推送尽量进行聚合。
     * 2、首次推送，只推用户币种层面资产不为0的账户信息。币种层面资产不为0的定义：eq、availEq、availBal 中任意一个字段不为0，即币种层面资产不为0。如果数据太大无法在单个推送消息中发送，它将被分成多个消息发送。
     * 3、定时推送，只推用户币种层面资产不为0的账户信息。币种层面资产不为0的定义：eq、availEq、availBal 中任意一个字段不为0，即币种层面资产不为0。
     * 例：按照所有币种订阅且有5个币种的余额或者权益都不为0，首次和定时推全部5个；账户下有一个币种余额或者权益改变，那么账户变更的触发只推这一个。
     * <p>
     * 币种余额小于 1e-8 的币种信息，会在 details 中过滤掉不返回。
     */
    ACCOUNT("account", "onOperateAccount", "onReceiveAccount"),

    /**
     * 获取持仓信息，首次订阅按照订阅维度推送数据，此外，当下单、撤单等事件触发时，推送数据以及按照订阅维度定时推送数据
     * 系统将限制订阅 WebSocket 频道的最大并发连接数。
     */
    POSITIONS("positions", "onOperatePositions", "onReceivePositions"),

    /**
     * 获取账户余额和持仓信息，首次订阅按照订阅维度推送数据，此外，当成交、资金划转等事件触发时，推送数据。
     * <p>
     * 该频道适用于尽快获取账户现金余额和仓位资产变化的信息。
     * 系统将限制订阅 WebSocket 频道的最大并发连接数。详情在限制 WebSocket 私有频道对应连接数量
     */
    BALANCE_AND_POSITION("balance_and_position", "onOperateBalanceAndPosition", "onReceiveBalanceAndPosition"),

    /**
     * 此推送频道仅作为风险提示，不建议作为策略交易的风险判断。
     * 在行情剧烈波动的情况下，可能会出现此消息推送的同时仓位已经被强平的可能性。
     * 预警会在某一个逐仓仓位有风险时推送。预警会在所有全仓仓位有风险时推送。
     * 系统将限制订阅 WebSocket 频道的最大并发连接数。详情在限制 WebSocket 私有频道对应连接数量
     */
    LIQUIDATION_WARNING("liquidation-warning", "onOperateLiquidationWarning", "onReceiveLiquidationWarning"),

    /**
     * 获取账户资产的greeks信息，首次订阅按照订阅维度推送数据，此外，当增加或者减少币种余额、持仓数量等事件触发时，推送数据以及按照订阅维度定时推送数据
     * 系统将限制订阅 WebSocket 频道的最大并发连接数。详情在限制 WebSocket 私有频道对应连接数量
     */
    ACCOUNT_GREEKS("account-greeks", "onOperateAccountGreeks", "onReceiveAccountGreeks"),


    //****************** 业务频道，需验证即可使用 *************************/

    /**
     * 当发起充值或者充值状态发生变化时会触发消息推送。
     * 支持母账户或者子账户的订阅
     * <p>
     * 如果是母账户订阅，可以同时接受母账户与子账户的充值信息的推送
     * 如果是子账户订阅，则仅支持子账户充值信息的推送
     */
    DEPOSIT_INFO("deposit-info", "onOperateDepositInfo", "onReceiveDepositInfo"),

    /**
     * 当发起提币或者提币状态发生变化时会触发消息推送。
     * 支持母账户或者子账户的订阅
     * <p>
     * 如果是母账户订阅，可以同时接受母账户与子账户的提币信息的推送
     * 如果是子账户订阅，则仅支持子账户提币信息的推送
     */
    WITHDRAWAL_INFO("withdrawal-info", "onOperateWithdrawalInfo", "onReceiveWithdrawalInfo"),

    /**
     * 标记价格K线频道
     * 获取标记价格的K线数据，推送频率最快是间隔1秒推送一次数据。
     */
    MARK_PRICE_CANDLE_3MON("mark-price-candle3M", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_1MON("mark-price-candle1M", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_1W("mark-price-candle1W", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_1D("mark-price-candle1D", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_2D("mark-price-candle2D", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_3D("mark-price-candle3D", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_5D("mark-price-candle5D", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_12H("mark-price-candle12H", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_6H("mark-price-candle6H", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_4H("mark-price-candle4H", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_2H("mark-price-candle2H", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_1H("mark-price-candle1H", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_30M("mark-price-candle30m", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_15M("mark-price-candle15m", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_5M("mark-price-candle5m", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_3M("mark-price-candle3m", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_1M("mark-price-candle1m", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_3M_UTC("mark-price-candle3Mutc", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_1M_UTC("mark-price-candle1Mutc", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_1W_UTC("mark-price-candle1Wutc", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_1D_UTC("mark-price-candle1Dutc", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_2D_UTC("mark-price-candle2Dutc", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_3D_UTC("mark-price-candle3Dutc", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_5D_UTC("mark-price-candle5Dutc", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_2H_UTC("mark-price-candle12Hutc", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),
    MARK_PRICE_CANDLE_6H_UTC("mark-price-candle6Hutc", "onOperateMarkPriceCandle", "onReceiveMarkPriceCandle"),

    /**
     * 指数K线频道
     * 获取指数的K线数据，推送频率最快是间隔1秒推送一次数据。
     */
    INDEX_CANDLE_3MON("index-candle3M", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_1MON("index-candle1M", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_1W("index-candle1W", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_1D("index-candle1D", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_2D("index-candle2D", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_3D("index-candle3D", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_5D("index-candle5D", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_12H("index-candle12H", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_6H("index-candle6H", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_4H("index-candle4H", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_2H("index-candle2H", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_1H("index-candle1H", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_30M("index-candle30m", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_15M("index-candle15m", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_5M("index-candle5m", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_3M("index-candle3m", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_1M("index-candle1m", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_3M_UTC("index-candle3Mutc", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_1M_UTC("index-candle1Mutc", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_1W_UTC("index-candle1Wutc", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_1D_UTC("index-candle1Dutc", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_2D_UTC("index-candle2Dutc", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_3D_UTC("index-candle3Dutc", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_5D_UTC("index-candle5Dutc", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_2H_UTC("index-candle12Hutc", "onOperateIndexCandle", "onReceiveIndexCandle"),
    INDEX_CANDLE_6H_UTC("index-candle6Hutc", "onOperateIndexCandle", "onReceiveIndexCandle"),

    /**
     * 经济日历频道
     * 获取最新经济日历数据。 该频道仅开放给交易费等级VIP1及以上的用户。
     */
    ECONOMIC_CALENDAR("economic-calendar", "onOperateEconomicCalendar", "onReceiveEconomicCalendar"),
    ;

    private final String value;
    private final String optCallbackMethodName;
    private final String rcvCallbackMethodName;

    Channel(final String value, String optCallbackMethodName, String rcvCallbackMethodName) {
        this.value = value;
        this.optCallbackMethodName = optCallbackMethodName;
        this.rcvCallbackMethodName = rcvCallbackMethodName;
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
