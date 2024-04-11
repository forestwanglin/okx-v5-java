package xyz.felh.okx.v5;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;

import java.time.Duration;

import static xyz.felh.okx.v5.OkxApiService.*;

@Slf4j
public class OkxServiceTest {

//    private OkxApiService getOkxService() {
//        String sk = System.getenv("OPENAI_TOKEN");
//        ObjectMapper mapper = defaultObjectMapper();
////        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 7890));
//        OkHttpClient client = defaultClient(sk, Duration.ofMillis(300000))
//                .newBuilder()
////                .addInterceptor(new ExtractHeaderInterceptor(responseHeaders -> log.info("headers: {}", JSON.toJSONString(responseHeaders))))
////                .proxy(proxy)
//                .build();
//        Retrofit retrofit = defaultRetrofit(client, mapper);
//        OkxApi api = retrofit.create(OkxApi.class);
//        return new OkxApiService(api, client);
//    }
//
//    @Test
//    public void test() {
//        Object models = getOkxService().test();
//        log.info("test: " + models);
//    }

}
