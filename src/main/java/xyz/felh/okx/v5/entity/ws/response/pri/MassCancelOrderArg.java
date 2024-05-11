package xyz.felh.okx.v5.entity.ws.response.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class MassCancelOrderArg implements WsResponseArg {

    /**
     * Result of the request true, false
     */
    @JSONField(name = "result")
    @JsonProperty("result")
    private Boolean result;

}
