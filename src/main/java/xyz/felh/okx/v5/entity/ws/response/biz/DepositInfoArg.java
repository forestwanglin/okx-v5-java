package xyz.felh.okx.v5.entity.ws.response.biz;

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
public class DepositInfoArg extends WsResponseArg {

    @Builder.Default
    private Channel channel = Channel.DEPOSIT_INFO;

    /**
     * 币种名称，如 BTC
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 用户标识
     */
    @JSONField(name = "uid")
    @JsonProperty("uid")
    private String uid;

}
