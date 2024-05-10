package xyz.felh.okx.v5.entity.ws.response;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.entity.ws.request.Operation;

import java.util.List;

@Slf4j
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class WsOnceResponse<T> implements IWsResponse {

    /**
     * Unique identifier of the message
     * Provided by client. It will be returned in response message for identifying the corresponding request.
     * A combination of case-sensitive alphanumerics, all numbers, or all letters of up to 32 characters.
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    private String id;

    @JSONField(name = "op")
    @JsonProperty("op")
    private Operation op;

    @JSONField(name = "data")
    @JsonProperty("data")
    private List<T> data;

    /**
     * 错误码
     */
    @JSONField(name = "code")
    @JsonProperty("code")
    private String code;

    /**
     * 错误消息
     */
    @JSONField(name = "msg")
    @JsonProperty("msg")
    private String msg;

    /**
     * Timestamp at Websocket gateway when the request is received, Unix timestamp format in microseconds, e.g. 1597026383085123
     */
    @JSONField(name = "inTime")
    @JsonProperty("inTime")
    private Long inTime;

    /**
     * Timestamp at Websocket gateway when the response is sent, Unix timestamp format in microseconds, e.g. 1597026383085123
     */
    @JSONField(name = "outTime")
    @JsonProperty("outTime")
    private Long outTime;

    public static <T> WsOnceResponse<T> tryParse(Class<T> tClass, String message) {
        try {
            return JSONObject.parseObject(message, new TypeReference<>() {
            });
        } catch (Exception ex) {
            log.error("tryParse error", ex);
        }
        return null;
    }

}
