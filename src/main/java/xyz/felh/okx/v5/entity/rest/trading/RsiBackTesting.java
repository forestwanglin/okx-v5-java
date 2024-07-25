package xyz.felh.okx.v5.entity.rest.trading;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.rest.IOkxRestRsp;
import xyz.felh.okx.v5.entity.rest.IRestEntity;

/**
 * @author Forest Wang
 * @package xyz.felh.okx.v5.entity.rest.trading
 * @class RsiBackTesting
 * @email forestwanglin@gmail.cn
 * @date 2024/7/25
 */
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RsiBackTesting implements IRestEntity, IOkxRestRsp {

    /**
     * 触发次数
     */
    @JSONField(name = "triggerNum")
    @JsonProperty("triggerNum")
    private Integer triggerNum;

}