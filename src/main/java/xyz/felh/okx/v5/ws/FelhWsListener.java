package xyz.felh.okx.v5.ws;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.felh.okx.v5.OkxWsApiService;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;
import xyz.felh.okx.v5.entity.ws.response.CommonResponse;
import xyz.felh.okx.v5.entity.ws.response.ErrorResponse;
import xyz.felh.okx.v5.entity.ws.response.LoginResponse;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;
import xyz.felh.okx.v5.enumeration.ws.WsChannel;
import xyz.felh.okx.v5.handler.WsSubscribeEntityHandler;
import xyz.felh.okx.v5.handler.WsSubscribeEntityHandlerFactory;

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

    public FelhWsListener(WsChannel wsChannel, OkxWsApiService okxWsApiService) {
        this.okxWsApiService = okxWsApiService;
        this.wsChannel = wsChannel;
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

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String message) {
        log.debug("WebSocket message: {} {}", wsChannel, message);
        if (HEARTBEAT_RSP_MESSAGE.equals(message)) {
            log.debug("WebSocket heartbeat response {}", message);
        } else {
            // error message
            CommonResponse errorResponse = CommonResponse.tryParse(message, ErrorResponse.class);
            if (errorResponse != null) {
                okxWsApiService.getWsMessageListener().onOperateError((ErrorResponse) errorResponse);
                return;
            }
            // login message
            CommonResponse loginResponse = CommonResponse.tryParse(message, LoginResponse.class);
            if (loginResponse != null) {
                okxWsApiService.setHasLogin(true);
                okxWsApiService.getWsMessageListener().onLoginSuccess();
                okxWsApiService.getSubscribeStateService().restoreSubscribed(WsChannel.PRIVATE);
                return;
            }
            // response and subscribe response
            WsSubscribeEntityHandler<? extends WsRequestArg, ? extends WsResponseArg, ? extends WsSubscribeEntity> handler
                    = WsSubscribeEntityHandlerFactory.getHandler(message, wsChannel, okxWsApiService.getSubscribeStateService());
            handler.handle(okxWsApiService.getWsMessageListener());
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
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        log.info("onClosing: {} {} {} {}", wsChannel, webSocket, code, reason);
        okxWsApiService.setWebSocket(wsChannel, null);
        okxWsApiService.resetConnectCount(wsChannel);
        okxWsApiService.setConnectState(wsChannel, false);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        log.info("onFailure: {} {} {} {}", wsChannel, webSocket, t, response);
        okxWsApiService.setWebSocket(wsChannel, null);
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
