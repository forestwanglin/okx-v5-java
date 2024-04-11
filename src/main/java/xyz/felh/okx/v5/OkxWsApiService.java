package xyz.felh.okx.v5;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okio.ByteString;
import xyz.felh.okx.v5.enumeration.WsChannel;
import xyz.felh.okx.v5.ws.BusinessWsListener;
import xyz.felh.okx.v5.ws.PrivateWsListener;
import xyz.felh.okx.v5.ws.PublicWsListener;

import java.time.Duration;
import java.util.HashMap;
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

    public OkxWsApiService() {
        this(false);
    }

    public OkxWsApiService(boolean simulated) {
        this(defaultClient(Duration.ofSeconds(PING_INTERVAL_SEC)), simulated);
    }

    public OkxWsApiService(final OkHttpClient client, boolean simulated) {
//        this.api = api;
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

}