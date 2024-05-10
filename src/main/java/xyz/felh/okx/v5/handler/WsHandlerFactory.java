package xyz.felh.okx.v5.handler;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.OkxWsApiService;
import xyz.felh.okx.v5.entity.ws.biz.*;
import xyz.felh.okx.v5.entity.ws.pri.*;
import xyz.felh.okx.v5.entity.ws.pub.*;
import xyz.felh.okx.v5.entity.ws.request.Operation;
import xyz.felh.okx.v5.entity.ws.response.Event;
import xyz.felh.okx.v5.entity.ws.response.biz.*;
import xyz.felh.okx.v5.entity.ws.response.pri.*;
import xyz.felh.okx.v5.entity.ws.response.pub.*;
import xyz.felh.okx.v5.enumeration.ws.Channel;
import xyz.felh.okx.v5.enumeration.ws.WsChannel;

import static xyz.felh.okx.v5.constant.OkxConstants.*;

@Slf4j
public class WsHandlerFactory {

    public static WsHandler getHandler(WsChannel wsChannel,
                                       OkxWsApiService okxWsApiService,
                                       String message) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        String event = jsonObject.getString(RSP_EVENT);
        if (event != null) {
            Event evt = Event.fromValue(event);
            // login handler
            if (evt == Event.LOGIN) {
                return new WsLoginHandler(wsChannel, message);
            }

            // error
            if (evt == Event.ERROR) {
                return new WsErrorHandler(wsChannel, message);
            }

            // subscribe / unsubscribe
            if (evt == Event.SUBSCRIBE || evt == Event.UNSUBSCRIBE) {
                return getSubscribeHandler(wsChannel, message, false);
            }
        }

        // subscribe push entity
        if (jsonObject.containsKey(RSP_ARG) && jsonObject.containsKey(RSP_DATA)) {
            return getSubscribeHandler(wsChannel, message, true);
        }

        // once request
        Operation operation = Operation.fromValue(JSONObject.parseObject(message).getString(RSP_OP));
        if (jsonObject.containsKey(RSP_ID) && operation != null) {
            return getOnceHandler(wsChannel, operation, message);
        }

        return null;
    }

    public static WsHandler getOnceHandler(WsChannel wsChannel,
                                           Operation operation,
                                           String message) {
        return switch (operation) {
            // private
            case ORDER -> new WsOnceHandler<>(PlaceOrderArg.class, wsChannel, message, operation);
            // business
            // public
            default -> null;
        };
    }

    private static WsHandler getSubscribeHandler(
            WsChannel wsChannel,
            String message,
            boolean pushData) {
        Channel channel = Channel.fromValue(JSONObject.parseObject(message).getJSONObject(RSP_ARG).getString(RSP_CHANNEL));
        assert channel != null;
        return switch (channel) {
            // private
            case ACCOUNT -> pushData ?
                    new WsSubscribePushHandler<>(AccountArg.class, Account.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pri.AccountArg.class,
                            channel, message, wsChannel);
            case POSITIONS -> pushData ?
                    new WsSubscribePushHandler<>(PositionsArg.class, Positions.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pri.PositionsArg.class,
                            channel, message, wsChannel);
            case BALANCE_AND_POSITION -> pushData ?
                    new WsSubscribePushHandler<>(BalanceAndPositionArg.class, BalanceAndPosition.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pri.BalanceAndPositionArg.class,
                            channel, message, wsChannel);
            case LIQUIDATION_WARNING -> pushData ?
                    new WsSubscribePushHandler<>(LiquidationOrdersArg.class, LiquidationWarning.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.LiquidationOrdersArg.class,
                            channel, message, wsChannel);
            case ACCOUNT_GREEKS -> pushData ?
                    new WsSubscribePushHandler<>(AccountGreeksArg.class, AccountGreeks.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pri.AccountGreeksArg.class,
                            channel, message, wsChannel);
            case ORDERS -> pushData ?
                    new WsSubscribePushHandler<>(OrderArg.class, Order.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pri.OrderArg.class,
                            channel, message, wsChannel);
            // public
            case INSTRUMENTS -> pushData ?
                    new WsSubscribePushHandler<>(InstrumentsArg.class, Instruments.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.InstrumentsArg.class,
                            channel, message, wsChannel);
            case OPEN_INTEREST -> pushData ?
                    new WsSubscribePushHandler<>(OpenInterestArg.class, OpenInterest.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.OpenInterestArg.class,
                            channel, message, wsChannel);
            case FUNDING_RATE -> pushData ?
                    new WsSubscribePushHandler<>(FundingRateArg.class, FundingRate.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.FundingRateArg.class,
                            channel, message, wsChannel);
            case PRICE_LIMIT -> pushData ?
                    new WsSubscribePushHandler<>(PriceLimitArg.class, PriceLimit.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.PriceLimitArg.class,
                            channel, message, wsChannel);
            case OPT_SUMMARY -> pushData ?
                    new WsSubscribePushHandler<>(OptionSummaryArg.class, OptionSummary.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.OptionSummaryArg.class,
                            channel, message, wsChannel);
            case ESTIMATED_PRICE -> pushData ?
                    new WsSubscribePushHandler<>(EstimatedPriceArg.class, EstimatedPrice.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.EstimatedPriceArg.class,
                            channel, message, wsChannel);
            case MARK_PRICE -> pushData ?
                    new WsSubscribePushHandler<>(MarkPriceArg.class, MarkPrice.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.MarkPriceArg.class,
                            channel, message, wsChannel);
            case INDEX_TICKERS -> pushData ?
                    new WsSubscribePushHandler<>(IndexTickersArg.class, IndexTickers.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.IndexTickersArg.class,
                            channel, message, wsChannel);
            case LIQUIDATION_ORDERS -> pushData ?
                    new WsSubscribePushHandler<>(LiquidationOrdersArg.class, LiquidationOrders.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.LiquidationOrdersArg.class,
                            channel, message, wsChannel);
            case ADL_WARNINGS -> pushData ?
                    new WsSubscribePushHandler<>(AdlWarningArg.class, AdlWarning.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.pub.AdlWarningArg.class,
                            channel, message, wsChannel);
            // business
            case DEPOSIT_INFO -> pushData ?
                    new WsSubscribePushHandler<>(DepositInfoArg.class, DepositInfo.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.biz.DepositInfoArg.class,
                            channel, message, wsChannel);
            case WITHDRAWAL_INFO -> pushData ?
                    new WsSubscribePushHandler<>(WithdrawalInfoArg.class, WithdrawalInfo.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.biz.WithdrawalInfoArg.class,
                            channel, message, wsChannel);
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
                 MARK_PRICE_CANDLE_6H_UTC -> pushData ?
                    new WsSubscribePushHandler<>(MarkPriceCandlesticksArg.class, MarkPriceCandlesticks.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.biz.MarkPriceCandlesticksArg.class,
                            channel, message, wsChannel);
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
                 INDEX_CANDLE_6H_UTC -> pushData ?
                    new WsSubscribePushHandler<>(IndexCandlesticksArg.class, IndexCandlesticks.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.biz.IndexCandlesticksArg.class,
                            channel, message, wsChannel);
            case ECONOMIC_CALENDAR -> pushData ?
                    new WsSubscribePushHandler<>(EconomicCalendarArg.class, EconomicCalendar.class, channel, message, wsChannel) :
                    new WsSubscribeHandler<>(xyz.felh.okx.v5.entity.ws.request.biz.EconomicCalendarArg.class,
                            channel, message, wsChannel);
        };
    }

}
