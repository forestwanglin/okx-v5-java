package xyz.felh.okx.v5.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static xyz.felh.okx.v5.constant.OkxConstants.*;

/**
 * OkHttp Interceptor that adds an authorization header
 */
public class AuthenticationInterceptor implements Interceptor {

    private final String apiKey;
    private final String secretKey;
    private final String passphrase;
    private final boolean simulated;

    public AuthenticationInterceptor(String apiKey, String secretKey, String passphrase) {
        this(apiKey, secretKey, passphrase, false);
    }

    public AuthenticationInterceptor(String apiKey, String secretKey, String passphrase, boolean simulated) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.passphrase = passphrase;
        this.simulated = simulated;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request()
                .newBuilder()
                .header(HEADER_OK_ACCESS_KEY, apiKey)
                .header(HEADER_OK_ACCESS_SIGN, "")
                .header(HEADER_OK_ACCESS_TIMESTAMP, "")
                .header(HEADER_OK_ACCESS_PASSPHRASE, passphrase);
        if (simulated) {
            requestBuilder.header(HEADER_X_SIMULATED_TRADING, "1");
        }
        return chain.proceed(requestBuilder.build());
    }

}
