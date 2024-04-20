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
import xyz.felh.okx.v5.entity.ws.request.Operation;
import xyz.felh.okx.v5.entity.ws.request.WsRequest;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;
import xyz.felh.okx.v5.entity.ws.request.pri.*;
import xyz.felh.okx.v5.entity.ws.request.pub.InstrumentsArg;
import xyz.felh.okx.v5.entity.ws.request.pub.OpenInterestArg;
import xyz.felh.okx.v5.enumeration.WsChannel;
import xyz.felh.okx.v5.util.SignUtils;
import xyz.felh.okx.v5.ws.BusinessWsListener;
import xyz.felh.okx.v5.ws.PrivateWsListener;
import xyz.felh.okx.v5.ws.PublicWsListener;

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

    //    private final OkxApi api;
    private final OkHttpClient client;
    private final boolean simulated;
    private final Map<WsChannel, WebSocket> wsClientMap;
    private final Map<WsChannel, Integer> reconnectCountMap;
    private final Map<WsChannel, Boolean> isConnectMap;
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
        if (isConnect(wsChannel)) {
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
     * set it when connected
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
        if (reconnectCountMap.get(wsChannel) < MAX_RECONNECT_COUNT) {
            TimeUnit.MILLISECONDS.sleep(RECONNECT_INTERVAL);
            connect(wsChannel);
            reconnectCountMap.put(wsChannel, reconnectCountMap.get(wsChannel) + 1);
        } else {
            log.error("reconnect over {} {}, please check url or network!!!", MAX_RECONNECT_COUNT, wsChannel);
        }
    }

    /**
     * is connect
     *
     * @param wsChannel channel
     * @return isConnect
     */
    public boolean isConnect(WsChannel wsChannel) {
        return wsClientMap.get(wsChannel) != null && isConnectMap.get(wsChannel);
    }

//    public static ObjectMapper defaultObjectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
//        return mapper;
//    }

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
        if (!isConnect(wsChannel)) {
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
        if (!isConnect(wsChannel)) {
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
        if (!isConnect(wsChannel)) {
            return;
        }
        wsClientMap.get(wsChannel).close(code, reason);
    }

    // below is the api for business
    private void subscribe(WsChannel wsChannel, WsRequestArg requestArg) {
        WsRequest wsRequest = WsRequest.builder()
                .op(Operation.SUBSCRIBE)
                .args(List.of(requestArg)).build();
        send(wsChannel, JSON.toJSONString(wsRequest));
    }

    private void unsubscribe(WsChannel wsChannel, WsRequestArg requestArg) {
        WsRequest wsRequest = WsRequest.builder()
                .op(Operation.UNSUBSCRIBE)
                .args(List.of(requestArg)).build();
        send(wsChannel, JSON.toJSONString(wsRequest));
    }

    //********************************** private channel
    public void login(LoginArg loginArg, String secretKey) {
        loginArg.setSign(SignUtils.sign(loginArg, secretKey));
        WsRequest wsRequest = WsRequest.builder()
                .op(Operation.LOGIN)
                .args(List.of(loginArg)).build();
        send(WsChannel.PRIVATE, JSON.toJSONString(wsRequest));
    }

    /**
     * 账户频道
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
     * 持仓频道
     *
     * @param positionsArg
     */
    public void subscribePositions(PositionsArg positionsArg) {
        subscribe(WsChannel.PRIVATE, positionsArg);
    }

    public void unsubscribePositions(PositionsArg positionsArg) {
        unsubscribe(WsChannel.PRIVATE, positionsArg);
    }

    /**
     * 账户余额和持仓频道
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
     * 爆仓风险预警推送频道
     *
     * @param liquidationWarningArg
     */
    public void subscribeLiquidationWarning(LiquidationWarningArg liquidationWarningArg) {
        subscribe(WsChannel.PRIVATE, liquidationWarningArg);
    }

    public void unsubscribeLiquidationWarning(LiquidationWarningArg liquidationWarningArg) {
        unsubscribe(WsChannel.PRIVATE, liquidationWarningArg);
    }

    /**
     * 账户greeks频道
     *
     * @param accountGreeksArg
     */
    public void subscribeAccountGreeks(AccountGreeksArg accountGreeksArg) {
        subscribe(WsChannel.PRIVATE, accountGreeksArg);
    }

    public void unsubscribeAccountGreeks(AccountGreeksArg accountGreeksArg) {
        unsubscribe(WsChannel.PRIVATE, accountGreeksArg);
    }

    //********************************** public channel

    /**
     * 品频道
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
     * 持仓总量频道
     *
     * @param openInterestArg arg
     */
    public void subscribeOpenInterest(OpenInterestArg openInterestArg) {
        subscribe(WsChannel.PUBLIC, openInterestArg);
    }

    public void unsubscribeOpenInterest(OpenInterestArg openInterestArg) {
        unsubscribe(WsChannel.PUBLIC, openInterestArg);
    }
}