package xyz.felh.okx.v5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.PlaceOrderReq;
import xyz.felh.okx.v5.enumeration.OrderType;
import xyz.felh.okx.v5.enumeration.Side;
import xyz.felh.okx.v5.enumeration.ws.TdMode;

import java.math.BigDecimal;

@Slf4j
public class OkxServiceTest {

    private OkxApiService getOkxService() {
        // 模拟盘
        String apiKey = System.getenv("API_KEY");
        String passphrase = System.getenv("PASSPHRASE");
        String secretKey = System.getenv("SECRET_KEY");
        return new OkxApiService(apiKey, secretKey, passphrase, true);
    }

    @Test
    public void testGET() {
        OkxApiService service = getOkxService();
        final var accountBalanceOkxRestResponse = service.getBalance("BTC");
        log.info("test: {}", accountBalanceOkxRestResponse);
    }

    @Test
    public void placeOrder() {
        OkxApiService service = getOkxService();
        var response = service.placeOrder(PlaceOrderReq.builder()
                .instId("BTC-USDT")
                .tdMode(TdMode.ISOLATED)
                .side(Side.BUY)
                .ordType(OrderType.MARKET)
                .sz(BigDecimal.ONE)
                .build());

//        response = service.cancelOrder(CancelOrderReq.builder()
//                .instId("BTC-USDT")
//                .ordId("hell")
//                .build());


        log.info("test: {}", response);
    }


}
