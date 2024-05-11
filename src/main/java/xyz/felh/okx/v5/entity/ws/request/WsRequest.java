package xyz.felh.okx.v5.entity.ws.request;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.enumeration.ws.Operation;

import java.util.List;

@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class WsRequest<T extends WsRequestArg> {

    @JSONField(name = "op")
    @JsonProperty("op")
    private Operation op;

    @JSONField(name = "args")
    @JsonProperty("args")
    private List<T> args;

}
