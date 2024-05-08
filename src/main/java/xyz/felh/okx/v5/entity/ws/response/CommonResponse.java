package xyz.felh.okx.v5.entity.ws.response;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class CommonResponse implements IWsResponse {

    @JSONField(name = "event")
    @JsonProperty("event")
    private Event event;

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

    public static CommonResponse tryParse(String message, Class<? extends CommonResponse> tClass) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(message);
            String type = jsonObject.getString("event");
            if (type != null) {
                if (type.equals(Event.ERROR.getValue()) && tClass.equals(ErrorResponse.class)) {
                    return JSONObject.parseObject(message, tClass);
                }
                if (type.equals(Event.LOGIN.getValue()) && tClass.equals(LoginResponse.class)) {
                    return JSONObject.parseObject(message, tClass);
                }
            }
        } catch (Exception ex) {
            //
        }
        return null;
    }

}
