package xyz.felh.okx.v5;

import com.alibaba.fastjson2.JSON;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import xyz.felh.okx.v5.entity.ws.biz.IndexCandle;
import xyz.felh.okx.v5.entity.ws.pri.Account;
import xyz.felh.okx.v5.entity.ws.pub.OpenInterest;
import xyz.felh.okx.v5.entity.ws.request.Operation;
import xyz.felh.okx.v5.entity.ws.request.WsRequest;
import xyz.felh.okx.v5.entity.ws.request.biz.IndexCandleArg;
import xyz.felh.okx.v5.entity.ws.request.pub.InstrumentsArg;
import xyz.felh.okx.v5.entity.ws.request.pub.OpenInterestArg;
import xyz.felh.okx.v5.entity.ws.response.ErrorResponse;
import xyz.felh.okx.v5.entity.ws.response.WsResponse;
import xyz.felh.okx.v5.entity.ws.response.WsSubscribeResponse;
import xyz.felh.okx.v5.enumeration.ws.Channel;
import xyz.felh.okx.v5.enumeration.ws.InstrumentType;
import xyz.felh.okx.v5.enumeration.ws.WsChannel;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OkxWsServiceTest {

    private OkxWsApiService getOkxWsService() {
        return new OkxWsApiService(true);
    }

    @Test
    public void entityTest() {
        WsRequest wsRequest = WsRequest.builder()
                .op(Operation.SUBSCRIBE)
                .args(List.of(
                        InstrumentsArg.builder()
                                .channel(Channel.INSTRUMENTS)
                                .instType(InstrumentType.SPOT)
                                .build()
                )).build();
        log.info("{}: {}", wsRequest, JSON.toJSONString(wsRequest));
    }

    @Test
    public void test() throws InterruptedException {
        OkxWsApiService wsApiService = getOkxWsService();
        wsApiService.connect(WsChannel.PUBLIC);
//        wsApiService.connect(WsChannel.PRIVATE);
//        wsApiService.connect(WsChannel.BUSINESS);
        TimeUnit.SECONDS.sleep(5);

        wsApiService.setWsMessageListener(new WsMessageListener() {

            @Override
            public void onLoginSuccess() {
                log.info("onLoginSuccess");
            }

            @Override
            public void onOperateAccount(@NonNull WsResponse<xyz.felh.okx.v5.entity.ws.response.pri.AccountArg> response) {
                log.info("onOperateAccount: {}", JSON.toJSONString(response));
            }

            @Override
            public void onReceiveAccount(@NonNull WsSubscribeResponse<xyz.felh.okx.v5.entity.ws.response.pri.AccountArg, Account> response) {
                log.info("onReceiveAccount: {}", JSON.toJSONString(response));
            }

            @Override
            public void onOperateError(@NonNull ErrorResponse response) {
                log.info("onOperateError: {}", JSON.toJSONString(response));
            }

            @Override
            public void onReceiveOpenInterest(@NonNull WsSubscribeResponse<xyz.felh.okx.v5.entity.ws.response.pub.OpenInterestArg, OpenInterest> response) {
                log.info("onReceiveOpenInterest: {}", JSON.toJSONString(response));
            }

            @Override
            public void onOperateOpenInterest(@NonNull WsResponse<xyz.felh.okx.v5.entity.ws.response.pub.OpenInterestArg> response) {
                log.info("onOperateOpenInterest: {}", JSON.toJSONString(response));
            }

            @Override
            public void onOperateIndexCandle(@NonNull WsResponse<xyz.felh.okx.v5.entity.ws.response.biz.IndexCandleArg> response) {
                log.info("onOperateIndexCandle: {}", JSON.toJSONString(response));
            }

            @Override
            public void onReceiveIndexCandle(@NonNull WsSubscribeResponse<xyz.felh.okx.v5.entity.ws.response.biz.IndexCandleArg, IndexCandle> response) {
                log.info("onReceiveIndexCandle: {}", JSON.toJSONString(response));
            }
        });

//        wsApiService.subscribeAccount(AccountArg.builder()
//                .ccy("BTC")
//                .build());
        wsApiService.subscribeOpenInterest(OpenInterestArg.builder()
                .instId("LTC-USD-SWAP")
                .build());
//        TimeUnit.SECONDS.sleep(10L);
//        wsApiService.unsubscribeOpenInterest(OpenInterestArg.builder()
//                .instId("LTC-USD-SWAP")
//                .build());

        TimeUnit.SECONDS.sleep(5);
        // 模拟盘
//        String apiKey = System.getenv("API_KEY");
//        String passphrase = System.getenv("PASSPHRASE");
//        String secretKey = System.getenv("SECRET_KEY");
//        wsApiService.login(LoginArg.builder()
//                .apiKey(apiKey)
//                .passphrase(passphrase)
//                .timestamp(System.currentTimeMillis() / 1000 + "")
//                .build(), secretKey);

//        TimeUnit.SECONDS.sleep(5);
//        wsApiService.subscribeAccount(AccountArg.builder()
//                .ccy("BTC")
//                .build());

//        wsApiService.subscribePositions(PositionsArg.builder()
//                .instType(InstrumentType.ANY)
//                .build());

//        wsApiService.subscribeBalanceAndPosition(BalanceAndPositionArg.builder().build());
//        wsApiService.subscribeLiquidationWarning(LiquidationWarningArg.builder()
//                        .instType(InstrumentType.ANY)
//                .build());
//        wsApiService.subscribeAccountGreeks(AccountGreeksArg.builder().build());
//        wsApiService.subscribeDepositInfo(DepositInfoArg.builder()
//                .ccy("BTC")
//                .build());

        wsApiService.subscribeIndexCandle(IndexCandleArg.builder()
                .channel(Channel.INDEX_CANDLE_1M)
                .instId("BTC-USD")
                .build());
        TimeUnit.MINUTES.sleep(30L);
    }

}
