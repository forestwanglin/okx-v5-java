package xyz.felh.okx.v5.handler;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.entity.ws.biz.*;
import xyz.felh.okx.v5.entity.ws.pri.*;
import xyz.felh.okx.v5.entity.ws.pub.*;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;
import xyz.felh.okx.v5.entity.ws.response.biz.*;
import xyz.felh.okx.v5.entity.ws.response.pri.*;
import xyz.felh.okx.v5.entity.ws.response.pub.*;
import xyz.felh.okx.v5.enumeration.ws.Channel;
import xyz.felh.okx.v5.enumeration.ws.WsChannel;
import xyz.felh.okx.v5.ws.SubscribeStateService;

import static xyz.felh.okx.v5.constant.OkxConstants.RSP_ARG;
import static xyz.felh.okx.v5.constant.OkxConstants.RSP_CHANNEL;

@Slf4j
public class WsSubscribeEntityHandlerFactory {

    public static WsSubscribeEntityHandler<? extends WsRequestArg, ? extends WsResponseArg, ? extends WsSubscribeEntity> getHandler(
            String message,
            WsChannel wsChannel,
            SubscribeStateService subscribeStateService) {
        Channel channel = Channel.fromValue(JSONObject.parseObject(message).getJSONObject(RSP_ARG).getString(RSP_CHANNEL));
        assert channel != null;
        return switch (channel) {
            // private
            case ACCOUNT -> new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pri.AccountArg.class,
                    AccountArg.class, Account.class, channel, message, wsChannel, subscribeStateService);
            case POSITIONS -> new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pri.PositionsArg.class,
                    PositionsArg.class, Positions.class, channel, message, wsChannel, subscribeStateService);
            case BALANCE_AND_POSITION ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pri.BalanceAndPositionArg.class,
                            BalanceAndPositionArg.class, BalanceAndPosition.class, channel, message, wsChannel, subscribeStateService);
            case LIQUIDATION_WARNING ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.LiquidationOrdersArg.class,
                            LiquidationOrdersArg.class, LiquidationWarning.class, channel, message, wsChannel, subscribeStateService);
            case ACCOUNT_GREEKS ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pri.AccountGreeksArg.class,
                            AccountGreeksArg.class, AccountGreeks.class, channel, message, wsChannel, subscribeStateService);
            case ORDERS -> new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pri.OrderArg.class,
                    OrderArg.class, Order.class, channel, message, wsChannel, subscribeStateService);
            // public
            case INSTRUMENTS ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.InstrumentsArg.class,
                            InstrumentsArg.class, Instruments.class, channel, message, wsChannel, subscribeStateService);
            case OPEN_INTEREST ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.OpenInterestArg.class,
                            OpenInterestArg.class, OpenInterest.class, channel, message, wsChannel, subscribeStateService);
            case FUNDING_RATE ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.FundingRateArg.class,
                            FundingRateArg.class, FundingRate.class, channel, message, wsChannel, subscribeStateService);
            case PRICE_LIMIT ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.PriceLimitArg.class,
                            PriceLimitArg.class, PriceLimit.class, channel, message, wsChannel, subscribeStateService);
            case OPT_SUMMARY ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.OptionSummaryArg.class,
                            OptionSummaryArg.class, OptionSummary.class, channel, message, wsChannel, subscribeStateService);
            case ESTIMATED_PRICE ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.EstimatedPriceArg.class,
                            EstimatedPriceArg.class, EstimatedPrice.class, channel, message, wsChannel, subscribeStateService);
            case MARK_PRICE -> new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.MarkPriceArg.class,
                    MarkPriceArg.class, MarkPrice.class, channel, message, wsChannel, subscribeStateService);
            case INDEX_TICKERS ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.IndexTickersArg.class,
                            IndexTickersArg.class, IndexTickers.class, channel, message, wsChannel, subscribeStateService);
            case LIQUIDATION_ORDERS ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.LiquidationOrdersArg.class,
                            LiquidationOrdersArg.class, LiquidationOrders.class, channel, message, wsChannel, subscribeStateService);
            case ADL_WARNINGS ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.AdlWarningArg.class,
                            AdlWarningArg.class, AdlWarning.class, channel, message, wsChannel, subscribeStateService);
            // business
            case DEPOSIT_INFO ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.biz.DepositInfoArg.class,
                            DepositInfoArg.class, DepositInfo.class, channel, message, wsChannel, subscribeStateService);
            case WITHDRAWAL_INFO ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.biz.WithdrawalInfoArg.class,
                            WithdrawalInfoArg.class, WithdrawalInfo.class, channel, message, wsChannel, subscribeStateService);
            case MARK_PRICE_CANDLE_3MON,
                 MARK_PRICE_CANDLE_1MON,
                 MARK_PRICE_CANDLE_1W,
                 MARK_PRICE_CANDLE_1D,
                 MARK_PRICE_CANDLE_2D,
                 MARK_PRICE_CANDLE_3D,
                 MARK_PRICE_CANDLE_5D,
                 MARK_PRICE_CANDLE_12H,
                 MARK_PRICE_CANDLE_6H,
                 MARK_PRICE_CANDLE_4H,
                 MARK_PRICE_CANDLE_2H,
                 MARK_PRICE_CANDLE_1H,
                 MARK_PRICE_CANDLE_30M,
                 MARK_PRICE_CANDLE_15M,
                 MARK_PRICE_CANDLE_5M,
                 MARK_PRICE_CANDLE_3M,
                 MARK_PRICE_CANDLE_1M,
                 MARK_PRICE_CANDLE_3M_UTC,
                 MARK_PRICE_CANDLE_1M_UTC,
                 MARK_PRICE_CANDLE_1W_UTC,
                 MARK_PRICE_CANDLE_1D_UTC,
                 MARK_PRICE_CANDLE_2D_UTC,
                 MARK_PRICE_CANDLE_3D_UTC,
                 MARK_PRICE_CANDLE_5D_UTC,
                 MARK_PRICE_CANDLE_2H_UTC,
                 MARK_PRICE_CANDLE_6H_UTC ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.biz.MarkPriceCandlesticksArg.class,
                            MarkPriceCandlesticksArg.class, MarkPriceCandlesticks.class, channel, message, wsChannel, subscribeStateService);
            case INDEX_CANDLE_3MON,
                 INDEX_CANDLE_1MON,
                 INDEX_CANDLE_1W,
                 INDEX_CANDLE_1D,
                 INDEX_CANDLE_2D,
                 INDEX_CANDLE_3D,
                 INDEX_CANDLE_5D,
                 INDEX_CANDLE_12H,
                 INDEX_CANDLE_6H,
                 INDEX_CANDLE_4H,
                 INDEX_CANDLE_2H,
                 INDEX_CANDLE_1H,
                 INDEX_CANDLE_30M,
                 INDEX_CANDLE_15M,
                 INDEX_CANDLE_5M,
                 INDEX_CANDLE_3M,
                 INDEX_CANDLE_1M,
                 INDEX_CANDLE_3M_UTC,
                 INDEX_CANDLE_1M_UTC,
                 INDEX_CANDLE_1W_UTC,
                 INDEX_CANDLE_1D_UTC,
                 INDEX_CANDLE_2D_UTC,
                 INDEX_CANDLE_3D_UTC,
                 INDEX_CANDLE_5D_UTC,
                 INDEX_CANDLE_2H_UTC,
                 INDEX_CANDLE_6H_UTC ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.biz.IndexCandlesticksArg.class,
                            IndexCandlesticksArg.class, IndexCandlesticks.class, channel, message, wsChannel, subscribeStateService);
            case ECONOMIC_CALENDAR ->
                    new WsSubscribeEntityHandler<>(xyz.felh.okx.v5.entity.ws.request.biz.EconomicCalendarArg.class,
                            EconomicCalendarArg.class, EconomicCalendar.class, channel, message, wsChannel, subscribeStateService);
        };
    }

}
