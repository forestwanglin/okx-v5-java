package xyz.felh.okx.v5.ws;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.felh.okx.v5.OkxWsApiService;
import xyz.felh.okx.v5.enumeration.WsChannel;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static xyz.felh.okx.v5.constant.OkxConstants.HEARTBEAT_INTERVAL_SEC;
import static xyz.felh.okx.v5.constant.OkxConstants.HEARTBEAT_REQ_MESSAGE;

/**
 * felh ws listener
 */
@Slf4j
public abstract class FelhWsListener extends WebSocketListener {

    protected final OkxWsApiService okxWsApiService;
    private final WsChannel wsChannel;

    public FelhWsListener(OkxWsApiService okxWsApiService, WsChannel wsChannel) {
        this.okxWsApiService = okxWsApiService;
        this.wsChannel = wsChannel;
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        log.info("WebSocket opened {}", wsChannel);
        okxWsApiService.setWebSocket(wsChannel, webSocket);
        okxWsApiService.resetConnectCount(wsChannel);
        boolean isConnect = response.code() == 101;
        okxWsApiService.setConnectState(wsChannel, isConnect);
        if (isConnect) {
            log.info("WebSocket connect success");
            startHeartbeatThread();
        } else {
            okxWsApiService.reconnect(wsChannel);
        }
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        super.onMessage(webSocket, text);
        log.debug("WebSocket message: {} {}", wsChannel, text);
        log.info("entity: {}", JSON.toJSONString(MessageExtractor.extract(text)));
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
        super.onMessage(webSocket, bytes);
        log.info("WebSocket bytes message: {} {}", wsChannel, bytes);
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosed(webSocket, code, reason);
        log.info("onClosed: {} {} {} {}", wsChannel, webSocket, code, reason);
        okxWsApiService.setWebSocket(wsChannel, null);
        okxWsApiService.resetConnectCount(wsChannel);
        okxWsApiService.setConnectState(wsChannel, false);
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosing(webSocket, code, reason);
        log.info("onClosing: {} {} {} {}", wsChannel, webSocket, code, reason);
        okxWsApiService.setWebSocket(wsChannel, null);
        okxWsApiService.resetConnectCount(wsChannel);
        okxWsApiService.setConnectState(wsChannel, false);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
        log.info("onFailure: {} {} {} {}", wsChannel, webSocket, t, response);
        okxWsApiService.setWebSocket(wsChannel, null);
        okxWsApiService.resetConnectCount(wsChannel);
        okxWsApiService.setConnectState(wsChannel, false);
        if (t.getMessage() != null && t.getMessage().equals("Socket closed")) {
            okxWsApiService.reconnect(wsChannel);
        }
    }

    /**
     * start heartbeat thread
     */
    private void startHeartbeatThread() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, r -> {
            Thread thread = new Thread(Thread.currentThread().getThreadGroup(), r, "heartbeat-thread");
            thread.setDaemon(true);
            return thread;
        });
        executor.scheduleWithFixedDelay(
                () -> okxWsApiService.send(wsChannel, HEARTBEAT_REQ_MESSAGE),
                HEARTBEAT_INTERVAL_SEC, HEARTBEAT_INTERVAL_SEC, TimeUnit.SECONDS);
    }

}
