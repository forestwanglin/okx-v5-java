package xyz.felh.okx.v5.entity.ws.response;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.ws.WsSubUnsubArg;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WsResponse<T extends WsSubUnsubArg> implements IWsResponse {

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

    @Override
    public boolean ofInstance(String message) {
        try {
            JSONObject json = JSON.parseObject(message);
            if (json.containsKey("event") && json.containsKey("arg")) {
                return true;
            }
            if (json.containsKey("event") && Event.fromValue(json.getString("event")) == Event.ERROR) {
                return true;
            }
            if (json.containsKey("event") && Event.fromValue(json.getString("event")) == Event.LOGIN) {
                return true;
            }
        } catch (Exception ex) {
            //
        }
        return false;
    }
}
