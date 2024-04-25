package xyz.felh.okx.v5.entity.rest.trading.order.booking;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import xyz.felh.okx.v5.entity.rest.IOkxRestRsp;

@ToString
@Data
public class PlaceOrderRsp implements IOkxRestRsp {

    /**
     * 订单ID
     */
    @JSONField(name = "ordId")
    @JsonProperty("ordId")
    private String ordId;

    /**
     * 客户自定义订单ID
     */
    @JSONField(name = "clOrdId")
    @JsonProperty("clOrdId")
    private String clOrdId;

    /**
     * 订单标签
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;

    /**
     * 事件执行结果的code，0代表成功
     */
    @JSONField(name = "sCode")
    @JsonProperty("sCode")
    private Integer sCode;

    /**
     * 事件执行失败或成功时的msg
     */
    @JSONField(name = "sMsg")
    @JsonProperty("sMsg")
    private String sMsg;

}
