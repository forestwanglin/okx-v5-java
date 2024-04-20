package xyz.felh.okx.v5;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.entity.ws.pri.*;
import xyz.felh.okx.v5.entity.ws.pub.Instruments;
import xyz.felh.okx.v5.entity.ws.pub.OpenInterest;
import xyz.felh.okx.v5.entity.ws.response.WsResponse;
import xyz.felh.okx.v5.entity.ws.response.WsSubscribeResponse;
import xyz.felh.okx.v5.entity.ws.response.pri.*;
import xyz.felh.okx.v5.entity.ws.response.pub.InstrumentsArg;
import xyz.felh.okx.v5.entity.ws.response.pub.OpenInterestArg;

/**
 * handle all message from server
 */
@Slf4j
public abstract class WsMessageListener {

    public void onOperateError(@NonNull WsResponse<?> response) {
        log.debug("onOperateError: {}", response);
        log.debug("operate error, please ref https://www.okx.com/docs-v5/zh/#error-code-websocket.");
    }

    public void onLoginSuccess() {
        log.debug("onLoginSuccess");
    }

    public void onOperateAccount(@NonNull WsResponse<AccountArg> response) {
        log.debug("onOperateAccount: {}", response);
    }

    public void onReceiveAccount(@NonNull WsSubscribeResponse<AccountArg, Account> response) {
        log.debug("onReceiveAccount: {}", response);
    }

    public void onOperatePositions(@NonNull WsResponse<PositionsArg> response) {
        log.debug("onOperatePositions: {}", response);
    }

    public void onReceivePositions(@NonNull WsSubscribeResponse<PositionsArg, Positions> response) {
        log.debug("onReceivePositions: {}", response);
    }

    public void onOperateBalanceAndPosition(@NonNull WsResponse<BalanceAndPositionArg> response) {
        log.debug("onOperateBalanceAndPosition: {}", response);
    }

    public void onReceiveBalanceAndPosition(@NonNull WsSubscribeResponse<BalanceAndPositionArg, BalanceAndPosition> response) {
        log.debug("onReceiveBalanceAndPosition: {}", response);
    }

    public void onOperateLiquidationWarning(@NonNull WsResponse<LiquidationWarningArg> response) {
        log.debug("onOperateLiquidationWarning: {}", response);
    }

    public void onReceiveLiquidationWarning(@NonNull WsSubscribeResponse<LiquidationWarningArg, LiquidationWarning> response) {
        log.debug("onReceiveLiquidationWarning: {}", response);
    }

    public void onOperateAccountGreeks(@NonNull WsResponse<AccountGreeksArg> response) {
        log.debug("onOperateAccountGreeks: {}", response);
    }

    public void onReceiveAccountGreeks(@NonNull WsSubscribeResponse<AccountGreeksArg, AccountGreeks> response) {
        log.debug("onReceiveAccountGreeks: {}", response);
    }

    public void onOperateInstruments(WsResponse<InstrumentsArg> response) {
        log.debug("onOperateInstruments: {}", response);
    }

    public void onReceiveInstruments(@NonNull WsSubscribeResponse<InstrumentsArg, Instruments> response) {
        log.debug("onReceiveInstruments: {}", response);
    }

    public void onOperateOpenInterest(@NonNull WsResponse<OpenInterestArg> response) {
        log.debug("onOperateOpenInterest: {}", response);
    }

    public void onReceiveOpenInterest(@NonNull WsSubscribeResponse<OpenInterestArg, OpenInterest> response) {
        log.debug("onReceiveOpenInterest: {}", response);
    }

}
