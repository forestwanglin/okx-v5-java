package xyz.felh.okx.v5.entity.rest.trading.grid;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.rest.IOkxRestReq;

/**
 * @author Forest Wang
 * @package xyz.felh.okx.v5.entity.rest.trading.grid
 * @class CancelClosePositionOrderForContractGridReq
 * @email forestwanglin@gmail.cn
 * @date 2024/7/25
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelClosePositionOrderForContractGridReq implements IOkxRestReq {

    /**
     * 策略订单ID
     */
    @NonNull
    @JSONField(name = "algoId")
    @JsonProperty("algoId")
    private String algoId;

    /**
     * 平仓单ID
     */
    @NonNull
    @JSONField(name = "ordId")
    @JsonProperty("ordId")
    private String ordId;

}