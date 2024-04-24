package xyz.felh.okx.v5.entity.ws.response;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WsResponse<T> implements IWsResponse {

    @JSONField(name = "event")
    @JsonProperty("event")
    private Event event;

    @JSONField(name = "arg")
    @JsonProperty("arg")
    private T arg;

    /**
     * WebSocket连接ID
     */
    @JSONField(name = "connId")
    @JsonProperty("connId")
    private String connId;

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

    public WsResponse<T> tryParse(Class<T> tClass, String message) {
        try {
            WsResponse<JSONObject> response = JSONObject.parseObject(message, new TypeReference<>() {
            });
            if (response != null
                    && response.getEvent() != null
                    && response.getArg() != null
                    && (response.getEvent() == Event.SUBSCRIBE || response.getEvent() == Event.UNSUBSCRIBE)) {
                WsResponse<T> result = new WsResponse<>();
                result.setCode(response.getCode());
                result.setMsg(response.getMsg());
                result.setEvent(response.getEvent());
                result.setConnId(response.getConnId());
                result.setArg(JSONObject.parseObject(response.getArg().toString(), tClass));
                return result;
            }
        } catch (Exception ex) {
            log.error("tryParse error", ex);
        }
        return null;
    }
}
