package xyz.felh.okx.v5.entity.ws.response.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;
import xyz.felh.okx.v5.enumeration.ws.Channel;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AccountArg extends WsResponseArg {

    @Builder.Default
    private Channel channel = Channel.ACCOUNT;

    @NonNull
    @JSONField(name = "uid")
    @JsonProperty("uid")
    private String uid;

}

