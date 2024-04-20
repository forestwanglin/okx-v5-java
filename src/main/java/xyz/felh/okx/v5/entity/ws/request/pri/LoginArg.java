package xyz.felh.okx.v5.entity.ws.request.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsArg;


@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class LoginArg implements WsArg {

    /**
     * APIKey
     */
    @JSONField(name = "apiKey")
    @JsonProperty("apiKey")
    private String apiKey;

    /**
     * APIKey 的密码
     */
    @JSONField(name = "passphrase")
    @JsonProperty("passphrase")
    private String passphrase;

    /**
     * 时间戳，Unix Epoch时间，单位是秒
     */
    @JSONField(name = "timestamp")
    @JsonProperty("timestamp")
    private String timestamp;

    /**
     * 签名字符串
     */
    @JSONField(name = "sign")
    @JsonProperty("sign")
    private String sign;
}