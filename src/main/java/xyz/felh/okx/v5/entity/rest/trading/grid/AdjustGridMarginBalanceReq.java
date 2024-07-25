package xyz.felh.okx.v5.entity.rest.trading.grid;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.rest.IOkxRestReq;

import java.math.BigDecimal;

/**
 * @author Forest Wang
 * @package xyz.felh.okx.v5.entity.rest.trading.grid
 * @class AdjustGridMarginBalanceReq
 * @email forestwanglin@gmail.cn
 * @date 2024/7/25
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdjustGridMarginBalanceReq implements IOkxRestReq {

    /**
     * 策略订单ID
     */
    @NonNull
    @JSONField(name = "algoId")
    @JsonProperty("algoId")
    private String algoId;

    /**
     * 调整保证金类型
     * add：增加，reduce：减少
     */
    @NonNull
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;

    /**
     * 调整保证金数量
     * amt和percent必须传一个
     */
    @JSONField(name = "amt")
    @JsonProperty("amt")
    private BigDecimal amt;

    /**
     * 调整保证金百分比
     */
    @JSONField(name = "percent")
    @JsonProperty("percent")
    private BigDecimal percent;

}