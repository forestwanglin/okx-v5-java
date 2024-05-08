package xyz.felh.okx.v5.entity.ws.request.biz;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class IndexCandlesticksArg extends WsRequestArg {

    // 新建的时候必须指定channel

    /**
     * 现货指数，如 BTC-USD
     */
    @NonNull
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

}