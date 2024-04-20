package xyz.felh.okx.v5.entity.ws;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.enumeration.Channel;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class WsSubUnsubArg implements WsArg {

    @JSONField(name = "channel")
    @JsonProperty("channel")
    private Channel channel;

}
