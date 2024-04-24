package xyz.felh.okx.v5.entity.ws.response.pub;

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
public class FundingRateArg extends WsResponseArg {

    @Builder.Default
    private Channel channel = Channel.FUNDING_RATE;

    /**
     * 产品ID
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

}
