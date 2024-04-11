package xyz.felh.okx.v5.entity.ws.response;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static xyz.felh.okx.v5.constant.OkxConstants.HEARTBEAT_RSP_MESSAGE;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeartbeatResponse implements IWsResponse {

    @JSONField(name = "message")
    @JsonProperty("message")
    private String message;

    public boolean ofInstance(String message) {
        return HEARTBEAT_RSP_MESSAGE.equals(message);
    }

}