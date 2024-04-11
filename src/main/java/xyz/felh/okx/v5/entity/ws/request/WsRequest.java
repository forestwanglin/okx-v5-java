package xyz.felh.okx.v5.entity.ws.request;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsArg;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class WsRequest {

    @JSONField(name = "op")
    @JsonProperty("op")
    private Operation op;

    @JSONField(name = "args")
    @JsonProperty("args")
    private List<WsArg> args;

}
