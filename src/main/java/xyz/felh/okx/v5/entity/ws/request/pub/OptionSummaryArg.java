package xyz.felh.okx.v5.entity.ws.request.pub;

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
public class OptionSummaryArg extends WsRequestArg {

    @Builder.Default
    private Channel channel = Channel.OPT_SUMMARY;

    /**
     * 交易品种
     */
    @NonNull
    @JSONField(name = "instFamily")
    @JsonProperty("instFamily")
    private String instFamily;

}