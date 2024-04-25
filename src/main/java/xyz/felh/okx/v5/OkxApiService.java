package xyz.felh.okx.v5;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import xyz.felh.okx.v5.entity.rest.OkeHttpException;
import xyz.felh.okx.v5.entity.rest.OkxRestError;
import xyz.felh.okx.v5.entity.rest.OkxRestResponse;
import xyz.felh.okx.v5.entity.rest.account.Balance;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.CancelOrderReq;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.CancelOrderRsp;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.PlaceOrderReq;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.PlaceOrderRsp;
import xyz.felh.okx.v5.interceptor.AuthenticationInterceptor;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static xyz.felh.okx.v5.constant.OkxConstants.BASE_URL;
import static xyz.felh.okx.v5.constant.OkxConstants.SIM_BASE_URL;

@Slf4j
public class OkxApiService {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);
    private static final ObjectMapper errorMapper = defaultObjectMapper();
    private final boolean simulated;
    private final OkxApi api;
    private final OkHttpClient client;

    public OkxApiService(String apiKey, String secretKey, String passphrase) {
        this(apiKey, secretKey, passphrase, false);
    }

    public OkxApiService(String apiKey, String secretKey, String passphrase, boolean simulated) {
        this(buildApi(apiKey, secretKey, passphrase, simulated, DEFAULT_TIMEOUT),
                defaultClient(apiKey, secretKey, passphrase, simulated, DEFAULT_TIMEOUT),
                simulated);
    }

    public OkxApiService(final OkxApi api, final OkHttpClient client, boolean simulated) {
        this.api = api;
        this.client = client;
        this.simulated = simulated;
    }

    public static OkxApi buildApi(String apiKey, String secretKey, String passphrase, boolean simulated, Duration timeout) {
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(apiKey, secretKey, passphrase, simulated, timeout);
        Retrofit retrofit = defaultRetrofit(client, mapper, simulated);
        return retrofit.create(OkxApi.class);
    }

    public static ObjectMapper defaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        return mapper;
    }

    public static OkHttpClient defaultClient(String apiKey, String secretKey, String passphrase, boolean simulated, Duration timeout) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationInterceptor(apiKey, secretKey, passphrase, simulated))
                .connectionPool(new ConnectionPool(10, 4, TimeUnit.SECONDS))
                .readTimeout(timeout.toMillis(), TimeUnit.MILLISECONDS)
                .build();

    }

    public static Retrofit defaultRetrofit(OkHttpClient client, ObjectMapper mapper, boolean simulated) {
        return new Retrofit.Builder()
                .baseUrl(simulated ? SIM_BASE_URL : BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    /**
     * Calls the Open AI api, returns the response, and parses error messages if the request fails
     */
    public static <T> T execute(Single<T> apiCall) {
        try {
            return apiCall.blockingGet();
        } catch (HttpException e) {
            try {
                if (e.response() == null || e.response().errorBody() == null) {
                    throw e;
                }
                String errorBody = e.response().errorBody().string();
                OkxRestError error = errorMapper.readValue(errorBody, OkxRestError.class);
                throw new OkeHttpException(error, error.getMsg(), e);
            } catch (IOException ex) {
                // couldn't parse OpenAI error
                throw e;
            }
        }
    }

    // 开始接口

    /**
     * 查看账户余额
     *
     * @param ccy 支持多币种查询（不超过20个），币种之间半角逗号分隔
     * @return AccountBalance list
     */
    public OkxRestResponse<Balance> getBalance(String ccy) {
        return execute(api.getBalance(ccy));
    }

    /**
     * 下单
     *
     * @param request request
     * @return OkxRestResponse<TradeOrderRsp>
     */
    public OkxRestResponse<PlaceOrderRsp> placeOrder(PlaceOrderReq request) {
        return execute(api.placeOrder(request));
    }

    /**
     * 撤单
     *
     * @param request request
     * @return OkxRestResponse<CancelOrderRsp>
     */
    public OkxRestResponse<CancelOrderRsp> cancelOrder(CancelOrderReq request) {
        return execute(api.cancelOrder(request));
    }

}