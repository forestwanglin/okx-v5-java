package xyz.felh.okx.v5.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import xyz.felh.okx.v5.util.DateUtils;
import xyz.felh.okx.v5.util.SignUtils;

import java.io.IOException;
import java.util.Date;

import static xyz.felh.okx.v5.constant.OkxConstants.*;

/**
 * OkHttp Interceptor that adds an authorization header
 */
@Slf4j
public class AuthenticationInterceptor implements Interceptor {

    private final String apiKey;
    private final String secretKey;
    private final String passphrase;
    private final boolean simulated;

    public AuthenticationInterceptor(String apiKey, String secretKey, String passphrase, boolean simulated) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.passphrase = passphrase;
        this.simulated = simulated;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        String timestamp = DateUtils.format(DateUtils.FORMAT_UTC_ISO8601, new Date(), 0);
        String method = chain.request().method();
        String body = EMPTY;
        if (method.equalsIgnoreCase("POST")) {
            body = bodyToString(chain.request().body());
        }
        String sign = SignUtils.signRest(secretKey,
                timestamp,
                method,
                chain.request().url().encodedPath()
                        + (chain.request().url().encodedQuery() != null ? ("?" + chain.request().url().encodedQuery()) : EMPTY),
                body);
        Request.Builder requestBuilder = chain.request()
                .newBuilder()
                .header(HEADER_OK_ACCESS_KEY, apiKey)
                .header(HEADER_OK_ACCESS_SIGN, sign)
                .header(HEADER_OK_ACCESS_TIMESTAMP, timestamp)
                .header(HEADER_OK_ACCESS_PASSPHRASE, passphrase);
        if (simulated) {
            requestBuilder.header(HEADER_X_SIMULATED_TRADING, "1");
        }
        return chain.proceed(requestBuilder.build());
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            if (request != null) {
                request.writeTo(buffer);
            } else {
                return EMPTY;
            }
            return buffer.readUtf8();
        } catch (final IOException e) {
            log.error("Failed to serialize request body", e);
            return EMPTY;
        }
    }

}
