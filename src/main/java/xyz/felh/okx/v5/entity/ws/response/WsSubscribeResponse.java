package xyz.felh.okx.v5.entity.ws.response;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.ws.WsSubUnsubArg;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WsSubscribeResponse<T extends WsSubUnsubArg, D extends WsSubscribeEntity> implements IWsResponse {

    @JSONField(name = "arg")
    @JsonProperty("arg")
    private T arg;

    @JSONField(name = "data")
    @JsonProperty("data")
    private List<D> data;

    @Override
    public boolean ofInstance(String message) {
        try {
            JSONObject json = JSON.parseObject(message);
            if (json.containsKey("arg") && json.containsKey("data")) {
                return true;
            }
        } catch (Exception ex) {
            //
        }
        return false;
    }

}
