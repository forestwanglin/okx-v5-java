package xyz.felh.okx.v5;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

@Slf4j
public class OkxApiService {

    private final OkxApi api;

    private final OkHttpClient client;

//    public OkxApiService() {
//        this(buildApi(), defaultClient());
//    }

    public OkxApiService(final OkxApi api, final OkHttpClient client) {
        this.api = api;
        this.client = client;
    }

//    public static OkxApi buildApi() {
//        ObjectMapper mapper = defaultObjectMapper();
//        OkHttpClient client = defaultClient(token, timeout);
//        Retrofit retrofit = defaultRetrofit(client, mapper);
//        return retrofit.create(OkxApi.class);
//    }
//
//    public static ObjectMapper defaultObjectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
//        return mapper;
//    }
//
//    public static OkHttpClient defaultClient(String token, String orgId, Duration timeout) {
//        return new OkHttpClient.Builder()
//                .addInterceptor(new AuthenticationInterceptor(token, orgId))
//                .connectionPool(new ConnectionPool(10, 4, TimeUnit.SECONDS))
//                .readTimeout(timeout.toMillis(), TimeUnit.MILLISECONDS)
//                .build();
//
//    }
//
//    public static Retrofit defaultRetrofit(OkHttpClient client, ObjectMapper mapper) {
//        return new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(client)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(JacksonConverterFactory.create(mapper))
//                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//                .build();
//    }
}