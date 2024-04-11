package xyz.felh.okx.v5.entity.ws.pub;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.Channel;
import xyz.felh.okx.v5.entity.ws.InstrumentType;
import xyz.felh.okx.v5.entity.ws.WsArg;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class InstrumentsArg extends WsArg {

    @Builder.Default
    private Channel channel= Channel.INSTRUMENTS;

    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

}
