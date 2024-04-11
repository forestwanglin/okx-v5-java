package xyz.felh.okx.v5;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import xyz.felh.okx.v5.entity.ws.Channel;
import xyz.felh.okx.v5.entity.ws.InstrumentType;
import xyz.felh.okx.v5.entity.ws.WsArg;
import xyz.felh.okx.v5.entity.ws.pub.OpenInterestArg;
import xyz.felh.okx.v5.entity.ws.request.Operation;
import xyz.felh.okx.v5.entity.ws.request.WsRequest;
import xyz.felh.okx.v5.entity.ws.pub.InstrumentsArg;
import xyz.felh.okx.v5.enumeration.WsChannel;

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
        WsRequest wsRequest;
        wsApiService.connect(WsChannel.PUBLIC);
        TimeUnit.SECONDS.sleep(5);

//        WsRequest wsRequest = WsRequest.builder()
//                .op(Operation.SUBSCRIBE)
//                .args(List.of(
//                        InstrumentsArg.builder()
//                                .instType(InstrumentType.SPOT)
//                                .build()
//                )).build();
//        wsApiService.send(WsChannel.PUBLIC, JSON.toJSONString(wsRequest));
//        TimeUnit.SECONDS.sleep(5);


        wsRequest = WsRequest.builder()
                .op(Operation.SUBSCRIBE)
                .args(List.of(
                        OpenInterestArg.builder()
                                .instId("LTC-USD-SWAP")
                                .build()
                )).build();
        wsApiService.send(WsChannel.PUBLIC, JSON.toJSONString(wsRequest));

        TimeUnit.MINUTES.sleep(30L);
    }

}
