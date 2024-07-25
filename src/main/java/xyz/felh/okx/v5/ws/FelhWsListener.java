package xyz.felh.okx.v5.ws;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.felh.okx.v5.OkxWsApiService;
import xyz.felh.okx.v5.enumeration.ws.WsChannel;
import xyz.felh.okx.v5.handler.WsHandler;
import xyz.felh.okx.v5.handler.WsHandlerFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static xyz.felh.okx.v5.constant.OkxConstants.*;

/**
 * felh ws listener
 */
@Slf4j
public abstract class FelhWsListener extends WebSocketListener {

    protected final OkxWsApiService okxWsApiService;
    private final WsChannel wsChannel;
    private ScheduledFuture<?> heartbeatFuture;

    public FelhWsListener(WsChannel wsChannel, OkxWsApiService okxWsApiService) {
        this.okxWsApiService = okxWsApiService;
        this.wsChannel = wsChannel;
        this.heartbeatFuture = null;
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        log.info("WebSocket opened {}", wsChannel);
        okxWsApiService.setWebSocket(wsChannel, webSocket);
        okxWsApiService.resetConnectCount(wsChannel);
        boolean isConnected = response.code() == 101;
        okxWsApiService.setConnectState(wsChannel, isConnected);
        if (isConnected) {
            log.info("WebSocket connect success");
            startHeartbeatThread();
            afterConnected();
        } else {
            okxWsApiService.reconnect(wsChannel);
        }
    }

    protected void afterConnected() {
        //
    }

    private void afterDisconnected() {
        if (heartbeatFuture != null && !heartbeatFuture.isCancelled()) {
            // cancel heartbeat
            log.info("cancel heart beat");
            heartbeatFuture.cancel(true);
        }
        // 试着重连
        okxWsApiService.reconnect(wsChannel);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String message) {
        log.debug("WebSocket message: {} {}", wsChannel, message);
        if (HEARTBEAT_RSP_MESSAGE.equals(message)) {
            log.debug("WebSocket heartbeat response {}", message);
        } else {
            WsHandler handler = WsHandlerFactory.getHandler(wsChannel, okxWsApiService, message);
            if (handler != null) {
                handler.handle(okxWsApiService);
            }
        }
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
        log.info("WebSocket bytes message: {} {}", wsChannel, bytes);
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        log.info("onClosed: {} {} {} {}", wsChannel, webSocket, code, reason);
        okxWsApiService.setWebSocket(wsChannel, null);
        okxWsApiService.resetConnectCount(wsChannel);
        okxWsApiService.setConnectState(wsChannel, false);
        afterDisconnected();
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        log.info("onClosing: {} {} {} {}", wsChannel, webSocket, code, reason);
        okxWsApiService.setWebSocket(wsChannel, null);
        okxWsApiService.resetConnectCount(wsChannel);
        okxWsApiService.setConnectState(wsChannel, false);
        afterDisconnected();
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        log.error("onFailure: {} {} {}", wsChannel, webSocket, response, t);
        okxWsApiService.setWebSocket(wsChannel, null);
        okxWsApiService.setConnectState(wsChannel, false);
        afterDisconnected();
//        if (t.getMessage() != null && t.getMessage().equals("Socket closed")) {
//            okxWsApiService.reconnect(wsChannel);
//        }
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
        heartbeatFuture = executor.scheduleWithFixedDelay(
                () -> okxWsApiService.send(wsChannel, HEARTBEAT_REQ_MESSAGE),
                HEARTBEAT_INTERVAL_SEC, HEARTBEAT_INTERVAL_SEC, TimeUnit.SECONDS);

    }

}
