package xyz.felh.okx.v5;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.PlaceOrderReq;
import xyz.felh.okx.v5.entity.ws.pri.Order;
import xyz.felh.okx.v5.enumeration.OrderType;
import xyz.felh.okx.v5.enumeration.Side;
import xyz.felh.okx.v5.enumeration.ws.TdMode;

import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;

import static xyz.felh.okx.v5.OkxApiService.buildApi;
import static xyz.felh.okx.v5.OkxApiService.defaultClient;

@Slf4j
public class OkxServiceTest {

    private OkxApiService getOkxService() {
        // 模拟盘
        String apiKey = System.getenv("API_KEY");
        String passphrase = System.getenv("PASSPHRASE");
        String secretKey = System.getenv("SECRET_KEY");
        Duration defaultTimeout = Duration.ofSeconds(30);
        boolean simulated = true;
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 7890));
        OkHttpClient client = defaultClient(apiKey, secretKey, passphrase, simulated, defaultTimeout)
                .newBuilder()
                .proxy(proxy)
                .build();
        return new OkxApiService(buildApi(apiKey, secretKey, passphrase, simulated, defaultTimeout), client, simulated);
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

    @Test
    public void testIssue2() throws JsonProcessingException {
        ObjectMapper objectMapper = defaultObjectMapper();
        Order order = objectMapper.readValue("{\"tpTriggerPxType\": \"a\"}", Order.class);
        System.out.println(order);
    }

    public static ObjectMapper defaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        return mapper;
    }

}
