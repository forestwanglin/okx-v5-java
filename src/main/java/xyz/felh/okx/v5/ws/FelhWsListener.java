package xyz.felh.okx.v5.ws;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.felh.okx.v5.OkxWsApiService;
import xyz.felh.okx.v5.entity.ws.pri.*;
import xyz.felh.okx.v5.entity.ws.pub.Instruments;
import xyz.felh.okx.v5.entity.ws.pub.OpenInterest;
import xyz.felh.okx.v5.entity.ws.response.Event;
import xyz.felh.okx.v5.entity.ws.response.IWsResponse;
import xyz.felh.okx.v5.entity.ws.response.WsResponse;
import xyz.felh.okx.v5.entity.ws.response.WsSubscribeResponse;
import xyz.felh.okx.v5.entity.ws.response.pri.*;
import xyz.felh.okx.v5.entity.ws.response.pub.InstrumentsArg;
import xyz.felh.okx.v5.entity.ws.response.pub.OpenInterestArg;
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
        IWsResponse response = MessageExtractor.extract(text);
        if (response != null) {
            if (okxWsApiService.getWsMessageListener() != null) {
                if (response instanceof WsResponse<?>) {
                    if (((WsResponse<?>) response).getEvent() == Event.ERROR) {
                        okxWsApiService.getWsMessageListener().onOperateError((WsResponse<?>) response);
                    } else if (((WsResponse<?>) response).getEvent() == Event.LOGIN) {
                        okxWsApiService.getWsMessageListener().onLoginSuccess();
                    } else {
                        switch (((WsResponse<?>) response).getArg().getChannel()) {
                            // private
                            case ACCOUNT ->
                                    okxWsApiService.getWsMessageListener().onOperateAccount((WsResponse<AccountArg>) response);
                            case POSITIONS ->
                                    okxWsApiService.getWsMessageListener().onOperatePositions((WsResponse<PositionsArg>) response);
                            case BALANCE_AND_POSITION ->
                                    okxWsApiService.getWsMessageListener().onOperateBalanceAndPosition((WsResponse<BalanceAndPositionArg>) response);
                            case LIQUIDATION_WARNING ->
                                    okxWsApiService.getWsMessageListener().onOperateLiquidationWarning((WsResponse<LiquidationWarningArg>) response);
                            case ACCOUNT_GREEKS ->
                                    okxWsApiService.getWsMessageListener().onOperateAccountGreeks((WsResponse<AccountGreeksArg>) response);
                            // public
                            case INSTRUMENTS ->
                                    okxWsApiService.getWsMessageListener().onOperateInstruments((WsResponse<InstrumentsArg>) response);
                            case OPEN_INTEREST ->
                                    okxWsApiService.getWsMessageListener().onOperateOpenInterest((WsResponse<OpenInterestArg>) response);
                        }
                    }
                } else if (response instanceof WsSubscribeResponse<?, ?>) {
                    switch (((WsSubscribeResponse<?, ?>) response).getArg().getChannel()) {
                        // private
                        case ACCOUNT ->
                                okxWsApiService.getWsMessageListener().onReceiveAccount((WsSubscribeResponse<AccountArg, Account>) response);
                        case POSITIONS ->
                                okxWsApiService.getWsMessageListener().onReceivePositions((WsSubscribeResponse<PositionsArg, Positions>) response);
                        case BALANCE_AND_POSITION ->
                                okxWsApiService.getWsMessageListener().onReceiveBalanceAndPosition((WsSubscribeResponse<BalanceAndPositionArg, BalanceAndPosition>) response);
                        case LIQUIDATION_WARNING ->
                                okxWsApiService.getWsMessageListener().onReceiveLiquidationWarning((WsSubscribeResponse<LiquidationWarningArg, LiquidationWarning>) response);
                        case ACCOUNT_GREEKS ->
                                okxWsApiService.getWsMessageListener().onReceiveAccountGreeks((WsSubscribeResponse<AccountGreeksArg, AccountGreeks>) response);
                        // public
                        case INSTRUMENTS ->
                                okxWsApiService.getWsMessageListener().onReceiveInstruments((WsSubscribeResponse<InstrumentsArg, Instruments>) response);
                        case OPEN_INTEREST ->
                                okxWsApiService.getWsMessageListener().onReceiveOpenInterest((WsSubscribeResponse<OpenInterestArg, OpenInterest>) response);
                    }
                }
            }
        }
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
