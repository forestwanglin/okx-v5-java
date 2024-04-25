package xyz.felh.okx.v5.entity.ws.request.biz;

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
public class DepositInfoArg extends WsRequestArg {

    @Builder.Default
    private Channel channel = Channel.DEPOSIT_INFO;

    @NonNull
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

}