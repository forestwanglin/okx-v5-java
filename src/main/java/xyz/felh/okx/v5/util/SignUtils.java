package xyz.felh.okx.v5.util;

import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.entity.ws.request.pri.LoginArg;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


@Slf4j
public class SignUtils {

    public static String signRest(String secretKey, String timestamp, String method, String path, String body) {
        String str = String.format("%s%s%s%s",
                timestamp, // timestamp
                method,  // method GET/POST
                path, // requestPath
                body // body
        );
        try {
            return Base64.getEncoder().encodeToString(hmacSHA256(secretKey.getBytes(), str.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String signWebsocket(LoginArg loginArg, String secretKey) {
        String str = String.format("%s%s%s",
                loginArg.getTimestamp(),
                "GET",
                "/users/self/verify");
        try {
            return Base64.getEncoder().encodeToString(hmacSHA256(secretKey.getBytes(), str.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * HmacSHA256算法,返回的结果始终是32位
     *
     * @param key     加密的键，可以是任何数据
     * @param content 待加密的内容
     * @return 加密后的内容
     * @throws Exception ex
     */
    public static byte[] hmacSHA256(byte[] key, byte[] content) throws Exception {
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        hmacSha256.init(new SecretKeySpec(key, 0, key.length, "HmacSHA256"));
        return hmacSha256.doFinal(content);
    }

}
