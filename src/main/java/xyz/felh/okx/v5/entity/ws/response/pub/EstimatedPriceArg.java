package xyz.felh.okx.v5.entity.ws.response.pub;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;
import xyz.felh.okx.v5.enumeration.Channel;
import xyz.felh.okx.v5.enumeration.InstrumentType;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class EstimatedPriceArg extends WsResponseArg {

    @Builder.Default
    private Channel channel = Channel.ESTIMATED_PRICE;

    /**
     * 产品类型
     * FUTURES：交割合约
     * SWAP：永续合约
     * OPTION：期权
     */
    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

    /**
     * 交易品种
     * instFamily和instId必须指定一个
     */
    @JSONField(name = "instFamily")
    @JsonProperty("instFamily")
    private String instFamily;


    /**
     * 产品ID
     * instFamily和instId必须指定一个
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

}

