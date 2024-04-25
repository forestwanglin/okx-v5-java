package xyz.felh.okx.v5.entity.ws.request.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;
import xyz.felh.okx.v5.enumeration.ws.Channel;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AccountGreeksArg extends WsRequestArg {

    @Builder.Default
    private Channel channel = Channel.ACCOUNT_GREEKS;

    /**
     * 币种
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;


}
