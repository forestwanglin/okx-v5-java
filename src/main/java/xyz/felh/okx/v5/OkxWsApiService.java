package xyz.felh.okx.v5;

import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okio.ByteString;
import xyz.felh.okx.v5.entity.ws.request.*;
import xyz.felh.okx.v5.entity.ws.request.biz.*;
import xyz.felh.okx.v5.entity.ws.request.pri.*;
import xyz.felh.okx.v5.entity.ws.request.pub.*;
import xyz.felh.okx.v5.enumeration.ws.WsChannel;
import xyz.felh.okx.v5.util.SignUtils;
import xyz.felh.okx.v5.ws.BusinessWsListener;
import xyz.felh.okx.v5.ws.PrivateWsListener;
import xyz.felh.okx.v5.ws.PublicWsListener;
import xyz.felh.okx.v5.ws.SubscribeStateService;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static xyz.felh.okx.v5.constant.OkxConstants.*;

@Slf4j
public class OkxWsApiService {

    private static final int PING_INTERVAL_SEC = 300;
    private static final int MAX_RECONNECT_COUNT = 5;
    private static final int RECONNECT_INTERVAL = 5000;

    private final OkHttpClient client;
    private final boolean simulated;
    private final Map<WsChannel, WebSocket> wsClientMap;
    private final Map<WsChannel, Integer> reconnectCountMap;
    private final Map<WsChannel, Boolean> isConnectMap;
    @Setter
    private boolean hasLogin;
    @Getter
    private final SubscribeStateService subscribeStateService;
    @Setter
    @Getter
    private WsMessageListener wsMessageListener;

    public OkxWsApiService() {
        this(false);
    }

    public OkxWsApiService(boolean simulated) {
        this(defaultClient(Duration.ofSeconds(PING_INTERVAL_SEC)), simulated);
    }

    public OkxWsApiService(final OkHttpClient client, boolean simulated) {
        this.client = client;
        this.simulated = simulated;
        this.wsClientMap = new HashMap<>();
        this.reconnectCountMap = new HashMap<>();
        this.isConnectMap = new HashMap<>();
        this.subscribeStateService = new SubscribeStateService(this);
        this.hasLogin = false;
        for (WsChannel c : WsChannel.values()) {
            this.reconnectCountMap.put(c, 0);
            this.isConnectMap.put(c, false);
        }
    }

    /**
     * connect ws
     *
     * @param wsChannel ws channel
     */
    public void connect(WsChannel wsChannel) {
        if (isConnected(wsChannel)) {
            log.info("ws connected: {}", wsChannel);
            return;
        }
        client.newWebSocket(defaultRequest(wsChannel, simulated),
                switch (wsChannel) {
                    case PUBLIC -> new PublicWsListener(this);
                    case PRIVATE -> new PrivateWsListener(this);
                    case BUSINESS -> new BusinessWsListener(this);
                });
    }

    /**
     * Set it when connected
     *
     * @param wsChannel ws channel
     * @param webSocket web socket
     */
    public void setWebSocket(WsChannel wsChannel, WebSocket webSocket) {
        wsClientMap.put(wsChannel, webSocket);
    }

    public void resetConnectCount(WsChannel wsChannel) {
        reconnectCountMap.put(wsChannel, 0);
    }

    public void setConnectState(WsChannel wsChannel, boolean state) {
        isConnectMap.put(wsChannel, state);
    }

    /**
     * reconnect
     *
     * @param wsChannel ws channel
     */
    @SneakyThrows
    public void reconnect(WsChannel wsChannel) {
        if (!isConnected(wsChannel)) {
            if (wsChannel == WsChannel.PRIVATE) {
                hasLogin = false;
            }
            log.debug("reconnect: {} count: {}", wsChannel, reconnectCountMap.get(wsChannel));
            if (reconnectCountMap.get(wsChannel) < MAX_RECONNECT_COUNT) {
                TimeUnit.MILLISECONDS.sleep(RECONNECT_INTERVAL);
                connect(wsChannel);
                reconnectCountMap.put(wsChannel, reconnectCountMap.get(wsChannel) + 1);
            } else {
                log.error("❌❌❌ Reconnect over {} times for channel {}, please check url or network!!!", MAX_RECONNECT_COUNT, wsChannel);
            }
        }
    }

    /**
     * IsConnected
     *
     * @param wsChannel channel
     * @return isConnect
     */
    public boolean isConnected(WsChannel wsChannel) {
        return wsClientMap.get(wsChannel) != null && isConnectMap.get(wsChannel);
    }

    public static Request defaultRequest(WsChannel wsChannel, boolean simulated) {
        return new Request.Builder()
                .url(switch (wsChannel) {
                    case PUBLIC -> simulated ? SIM_WS_PUBLIC_URL : WS_PUBLIC_URL;
                    case PRIVATE -> simulated ? SIM_WS_PRIVATE_URL : WS_PRIVATE_URL;
                    case BUSINESS -> simulated ? SIM_WS_BUSINESS_URL : WS_BUSINESS_URL;
                })
                .build();
    }

    public static OkHttpClient defaultClient(Duration pingInterval) {
        return new OkHttpClient.Builder()
                .pingInterval(pingInterval)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    /**
     * send string message
     *
     * @param wsChannel ws channel
     * @param message   message
     */
    public void send(WsChannel wsChannel, final String message) {
        log.info("send message: {} {}", wsChannel, message);
        if (!isConnected(wsChannel)) {
            log.info("websocket not connected: {}", wsChannel);
            return;
        }
        wsClientMap.get(wsChannel).send(message);
    }

    /**
     * send byte message
     *
     * @param wsChannel wx channel
     * @param message   message
     */
    public void send(WsChannel wsChannel, final ByteString message) {
        log.info("send byteMessage: {} {}", wsChannel, message);
        if (!isConnected(wsChannel)) {
            return;
        }
        wsClientMap.get(wsChannel).send(message);
    }

    /**
     * Manually close connection
     *
     * @param wsChannel ws channel
     * @param code      code
     * @param reason    reason
     */
    public void disconnect(WsChannel wsChannel, int code, String reason) {
        log.info("disconnect code: {} reason: {}", code, reason);
        if (!isConnected(wsChannel)) {
            return;
        }
        wsClientMap.get(wsChannel).close(code, reason);
    }

    // below is the api logic

    private void subscribe(WsChannel wsChannel, WsChannelRequestArg requestArg) {
        if (!subscribeStateService.hasSubscribed(wsChannel, requestArg)) {
            pureSubscribe(wsChannel, requestArg);
            subscribeStateService.addSubscribed(wsChannel, requestArg);
        } else {
            log.error("subscribe already exists: {} {}", wsChannel, requestArg);
        }
    }

    public void pureSubscribe(WsChannel wsChannel, WsChannelRequestArg requestArg) {
        WsRequest wsRequest = WsRequest.builder()
                .op(Operation.SUBSCRIBE)
                .args(List.of(requestArg)).build();
        send(wsChannel, JSON.toJSONString(wsRequest));
    }

    private void unsubscribe(WsChannel wsChannel, WsChannelRequestArg requestArg) {
        WsRequest wsRequest = WsRequest.builder()
                .op(Operation.UNSUBSCRIBE)
                .args(List.of(requestArg)).build();
        send(wsChannel, JSON.toJSONString(wsRequest));
    }

    private void sendOnceRequest(WsChannel wsChannel, String id, Operation operation, WsRequestArg wsRequestArg) {
        WsOnceRequest wsOnceRequest = WsOnceRequest.builder()
                .id(id)
                .op(operation)
                .args(List.of(wsRequestArg)).build();
        send(wsChannel, JSON.toJSONString(wsOnceRequest));
    }

    //********************************** private channel
    public void login(LoginArg loginArg, String secretKey) {
        if (hasLogin) {
            log.warn("already login");
            return;
        }
        loginArg.setSign(SignUtils.signWebsocket(loginArg, secretKey));
        WsRequest wsRequest = WsRequest.builder()
                .op(Operation.LOGIN)
                .args(List.of(loginArg)).build();
        send(WsChannel.PRIVATE, JSON.toJSONString(wsRequest));

        subscribeStateService.saveLoginInfo(loginArg.getApiKey(), loginArg.getPassphrase(), secretKey);
    }

    /**
     * Account channel
     *
     * @param accountArg arg
     */
    public void subscribeAccount(AccountArg accountArg) {
        subscribe(WsChannel.PRIVATE, accountArg);
    }

    public void unsubscribeAccount(AccountArg accountArg) {
        unsubscribe(WsChannel.PRIVATE, accountArg);
    }

    /**
     * Positions channel
     *
     * @param positionsArg positionsArg
     */
    public void subscribePositions(PositionsArg positionsArg) {
        subscribe(WsChannel.PRIVATE, positionsArg);
    }

    public void unsubscribePositions(PositionsArg positionsArg) {
        unsubscribe(WsChannel.PRIVATE, positionsArg);
    }

    /**
     * Balance and position channel
     *
     * @param balanceAndPositionArg arg
     */
    public void subscribeBalanceAndPosition(BalanceAndPositionArg balanceAndPositionArg) {
        subscribe(WsChannel.PRIVATE, balanceAndPositionArg);
    }

    public void unsubscribeBalanceAndPosition(BalanceAndPositionArg balanceAndPositionArg) {
        unsubscribe(WsChannel.PRIVATE, balanceAndPositionArg);
    }

    /**
     * position risk warning
     *
     * @param liquidationWarningArg liquidationWarningArg
     */
    public void subscribeLiquidationWarning(LiquidationWarningArg liquidationWarningArg) {
        subscribe(WsChannel.PRIVATE, liquidationWarningArg);
    }

    public void unsubscribeLiquidationWarning(LiquidationWarningArg liquidationWarningArg) {
        unsubscribe(WsChannel.PRIVATE, liquidationWarningArg);
    }

    /**
     * Account greeks channel
     *
     * @param accountGreeksArg accountGreeksArg
     */
    public void subscribeAccountGreeks(AccountGreeksArg accountGreeksArg) {
        subscribe(WsChannel.PRIVATE, accountGreeksArg);
    }

    public void unsubscribeAccountGreeks(AccountGreeksArg accountGreeksArg) {
        unsubscribe(WsChannel.PRIVATE, accountGreeksArg);
    }

    /**
     * Order channel
     *
     * @param orderArg orderArg
     */
    public void subscribeOrder(OrderArg orderArg) {
        subscribe(WsChannel.PRIVATE, orderArg);
    }

    public void unsubscribeOrder(OrderArg orderArg) {
        unsubscribe(WsChannel.PRIVATE, orderArg);
    }

    //********************************** public channel

    /**
     * Instuments channel
     *
     * @param instrumentsArg arg
     */
    public void subscribeInstruments(InstrumentsArg instrumentsArg) {
        subscribe(WsChannel.PUBLIC, instrumentsArg);
    }

    public void unsubscribeInstruments(InstrumentsArg instrumentsArg) {
        unsubscribe(WsChannel.PUBLIC, instrumentsArg);
    }

    /**
     * Open interest channel
     *
     * @param openInterestArg arg
     */
    public void subscribeOpenInterest(OpenInterestArg openInterestArg) {
        subscribe(WsChannel.PUBLIC, openInterestArg);
    }

    public void unsubscribeOpenInterest(OpenInterestArg openInterestArg) {
        unsubscribe(WsChannel.PUBLIC, openInterestArg);
    }

    /**
     * Funding rate channel
     *
     * @param fundingRateArg arg
     */
    public void subscribeFundingRate(FundingRateArg fundingRateArg) {
        subscribe(WsChannel.PUBLIC, fundingRateArg);
    }

    public void unsubscribeFundingRate(FundingRateArg fundingRateArg) {
        unsubscribe(WsChannel.PUBLIC, fundingRateArg);
    }

    /**
     * Price limit channel
     *
     * @param priceLimitArg arg
     */
    public void subscribePriceLimit(PriceLimitArg priceLimitArg) {
        subscribe(WsChannel.PUBLIC, priceLimitArg);
    }

    public void unsubscribePriceLimit(PriceLimitArg priceLimitArg) {
        unsubscribe(WsChannel.PUBLIC, priceLimitArg);
    }

    /**
     * option summary channel
     *
     * @param optionSummaryArg arg
     */
    public void subscribeOptionSummary(OptionSummaryArg optionSummaryArg) {
        subscribe(WsChannel.PUBLIC, optionSummaryArg);
    }

    public void unsubscribeOptionSummary(OptionSummaryArg optionSummaryArg) {
        unsubscribe(WsChannel.PUBLIC, optionSummaryArg);
    }

    /**
     * Estimated delivery/exercise price channel
     *
     * @param estimatedPriceArg arg
     */
    public void subscribeEstimatedPrice(EstimatedPriceArg estimatedPriceArg) {
        subscribe(WsChannel.PUBLIC, estimatedPriceArg);
    }

    public void unsubscribeEstimatedPrice(EstimatedPriceArg estimatedPriceArg) {
        unsubscribe(WsChannel.PUBLIC, estimatedPriceArg);
    }

    /**
     * Mark price channel
     *
     * @param markPriceArg arg
     */
    public void subscribeMarkPrice(MarkPriceArg markPriceArg) {
        subscribe(WsChannel.PUBLIC, markPriceArg);
    }

    public void unsubscribeMarkPrice(MarkPriceArg markPriceArg) {
        unsubscribe(WsChannel.PUBLIC, markPriceArg);
    }

    /**
     * Index tickers channel
     *
     * @param indexTickersArg arg
     */
    public void subscribeIndexTickers(IndexTickersArg indexTickersArg) {
        subscribe(WsChannel.PUBLIC, indexTickersArg);
    }

    public void unsubscribeIndexTickers(IndexTickersArg indexTickersArg) {
        unsubscribe(WsChannel.PUBLIC, indexTickersArg);
    }

    /**
     * Liquidation orders channel
     *
     * @param liquidationOrdersArg liquidationOrdersArg
     */
    public void subscribeLiquidationOrders(LiquidationOrdersArg liquidationOrdersArg) {
        subscribe(WsChannel.PUBLIC, liquidationOrdersArg);
    }

    public void unsubscribeLiquidationOrders(LiquidationOrdersArg liquidationOrdersArg) {
        unsubscribe(WsChannel.PUBLIC, liquidationOrdersArg);
    }

    /**
     * ADL warning channel
     *
     * @param adlWarningArg adlWarningArg
     */
    public void subscribeAdlWarning(AdlWarningArg adlWarningArg) {
        subscribe(WsChannel.PUBLIC, adlWarningArg);
    }

    public void unsubscribeAdlWarning(AdlWarningArg adlWarningArg) {
        unsubscribe(WsChannel.PUBLIC, adlWarningArg);
    }

    //********************************** business channel

    /**
     * Deposit info channel
     *
     * @param depositInfoArg depositInfoArg
     */
    public void subscribeDepositInfo(DepositInfoArg depositInfoArg) {
        subscribe(WsChannel.BUSINESS, depositInfoArg);
    }

    public void unsubscribeDepositInfo(DepositInfoArg depositInfoArg) {
        unsubscribe(WsChannel.BUSINESS, depositInfoArg);
    }

    /**
     * Withdrawal info channel
     *
     * @param withdrawalInfoArg withdrawalInfoArg
     */
    public void subscribeWithdrawalInfo(WithdrawalInfoArg withdrawalInfoArg) {
        subscribe(WsChannel.BUSINESS, withdrawalInfoArg);
    }

    public void unsubscribeWithdrawalInfo(WithdrawalInfoArg withdrawalInfoArg) {
        unsubscribe(WsChannel.BUSINESS, withdrawalInfoArg);
    }

    /**
     * Mark price candlesticks channel
     *
     * @param markPriceCandlesticksArg markPriceCandlesticksArg
     */
    public void subscribeMarkPriceCandlesticks(MarkPriceCandlesticksArg markPriceCandlesticksArg) {
        subscribe(WsChannel.BUSINESS, markPriceCandlesticksArg);
    }

    public void unsubscribeMarkPriceCandlesticks(MarkPriceCandlesticksArg markPriceCandlesticksArg) {
        unsubscribe(WsChannel.BUSINESS, markPriceCandlesticksArg);
    }

    /**
     * Index candlesticks channel
     *
     * @param indexCandlesticksArg indexCandlesticksArg
     */
    public void subscribeIndexCandlesticks(IndexCandlesticksArg indexCandlesticksArg) {
        subscribe(WsChannel.BUSINESS, indexCandlesticksArg);
    }

    public void unsubscribeIndexCandlesticks(IndexCandlesticksArg indexCandlesticksArg) {
        unsubscribe(WsChannel.BUSINESS, indexCandlesticksArg);
    }

    /**
     * Economic calendar channel
     *
     * @param economicCalendarArg economicCalendarArg
     */
    public void subscribeEconomicCalendar(EconomicCalendarArg economicCalendarArg) {
        subscribe(WsChannel.BUSINESS, economicCalendarArg);
    }

    public void unsubscribeEconomicCalendar(EconomicCalendarArg economicCalendarArg) {
        unsubscribe(WsChannel.BUSINESS, economicCalendarArg);
    }

    /**********  it is request and response once api below **********/

    // private channel

    /**
     * WS / Place order
     *
     * @param id            request id
     * @param placeOrderArg placeOrderArg
     */
    public void placeOrder(String id, PlaceOrderArg placeOrderArg) {
        sendOnceRequest(WsChannel.PRIVATE, id, Operation.ORDER, placeOrderArg);
    }

}