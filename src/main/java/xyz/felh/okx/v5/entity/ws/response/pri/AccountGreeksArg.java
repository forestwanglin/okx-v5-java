package xyz.felh.okx.v5.entity.ws.response.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;
import xyz.felh.okx.v5.enumeration.Channel;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AccountGreeksArg extends WsResponseArg {

    @Builder.Default
    private Channel channel = Channel.ACCOUNT_GREEKS;

    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

}

