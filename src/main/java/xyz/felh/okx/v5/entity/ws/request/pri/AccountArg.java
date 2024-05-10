package xyz.felh.okx.v5.entity.ws.request.pri;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.request.WsChannelRequestArg;
import xyz.felh.okx.v5.enumeration.ws.Channel;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;


@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AccountArg implements WsChannelRequestArg {

    @Builder.Default
    @JSONField(name = "channel")
    @JsonProperty("channel")
    private Channel channel = Channel.ACCOUNT;

    @NonNull
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 0: 仅根据账户事件推送数据
     * 若不添加该字段或将其设置为除0外的其他值，数据将根据事件推送并定时推送。
     * 使用该字段需严格遵守以下格式。
     * "extraParams": "
     * {
     * \"updateInterval\": \"0\"
     * }
     * "
     */
    @JSONField(name = "extraParams")
    @JsonProperty("extraParams")
    private String extraParams;

}
