package xyz.felh.okx.v5.handler;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.entity.ws.biz.*;
import xyz.felh.okx.v5.entity.ws.pri.*;
import xyz.felh.okx.v5.entity.ws.pub.*;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;
import xyz.felh.okx.v5.entity.ws.response.biz.*;
import xyz.felh.okx.v5.entity.ws.response.pri.AccountArg;
import xyz.felh.okx.v5.entity.ws.response.pri.AccountGreeksArg;
import xyz.felh.okx.v5.entity.ws.response.pri.BalanceAndPositionArg;
import xyz.felh.okx.v5.entity.ws.response.pri.PositionsArg;
import xyz.felh.okx.v5.entity.ws.response.pub.*;
import xyz.felh.okx.v5.enumeration.Channel;

import static xyz.felh.okx.v5.constant.OkxConstants.RSP_ARG;
import static xyz.felh.okx.v5.constant.OkxConstants.RSP_CHANNEL;

@Slf4j
public class WsSubscribeEntityHandlerFactory {

    public static WsSubscribeEntityHandler<? extends WsResponseArg, ? extends WsSubscribeEntity> getHandler(String message) {
        Channel channel = Channel.fromValue(JSONObject.parseObject(message).getJSONObject(RSP_ARG).getString(RSP_CHANNEL));
        assert channel != null;
        return switch (channel) {
            // private
            case ACCOUNT -> new WsSubscribeEntityHandler<>(AccountArg.class, Account.class, channel, message);
            case POSITIONS -> new WsSubscribeEntityHandler<>(PositionsArg.class, Positions.class, channel, message);
            case BALANCE_AND_POSITION ->
                    new WsSubscribeEntityHandler<>(BalanceAndPositionArg.class, BalanceAndPosition.class, channel, message);
            case LIQUIDATION_WARNING ->
                    new WsSubscribeEntityHandler<>(LiquidationOrdersArg.class, LiquidationWarning.class, channel, message);
            case ACCOUNT_GREEKS ->
                    new WsSubscribeEntityHandler<>(AccountGreeksArg.class, AccountGreeks.class, channel, message);
            // public
            case INSTRUMENTS ->
                    new WsSubscribeEntityHandler<>(InstrumentsArg.class, Instruments.class, channel, message);
            case OPEN_INTEREST ->
                    new WsSubscribeEntityHandler<>(OpenInterestArg.class, OpenInterest.class, channel, message);
            case FUNDING_RATE ->
                    new WsSubscribeEntityHandler<>(FundingRateArg.class, FundingRate.class, channel, message);
            case PRICE_LIMIT -> new WsSubscribeEntityHandler<>(PriceLimitArg.class, PriceLimit.class, channel, message);
            case OPT_SUMMARY -> new WsSubscribeEntityHandler<>(OptSummaryArg.class, OptSummary.class, channel, message);
            case ESTIMATED_PRICE ->
                    new WsSubscribeEntityHandler<>(EstimatedPriceArg.class, EstimatedPrice.class, channel, message);
            case MARK_PRICE -> new WsSubscribeEntityHandler<>(MarkPriceArg.class, MarkPrice.class, channel, message);
            case INDEX_TICKERS ->
                    new WsSubscribeEntityHandler<>(IndexTickersArg.class, IndexTickers.class, channel, message);
            case LIQUIDATION_ORDERS ->
                    new WsSubscribeEntityHandler<>(LiquidationOrdersArg.class, LiquidationOrders.class, channel, message);
            case ADL_WARNINGS ->
                    new WsSubscribeEntityHandler<>(AdlWarningArg.class, AdlWarning.class, channel, message);
            // business
            case DEPOSIT_INFO ->
                    new WsSubscribeEntityHandler<>(DepositInfoArg.class, DepositInfo.class, channel, message);
            case WITHDRAWAL_INFO ->
                    new WsSubscribeEntityHandler<>(WithdrawalInfoArg.class, WithdrawalInfo.class, channel, message);
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
                    new WsSubscribeEntityHandler<>(MarkPriceCandleArg.class, MarkPriceCandle.class, channel, message);
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
                    new WsSubscribeEntityHandler<>(IndexCandleArg.class, IndexCandle.class, channel, message);
            case ECONOMIC_CALENDAR ->
                    new WsSubscribeEntityHandler<>(EconomicCalendarArg.class, EconomicCalendar.class, channel, message);
        };
    }

}
