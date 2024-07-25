package xyz.felh.okx.v5.entity.rest.trading.grid;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.rest.IOkxRestRsp;
import xyz.felh.okx.v5.entity.rest.IRestEntity;
import xyz.felh.okx.v5.enumeration.AlgoOrderType;
import xyz.felh.okx.v5.enumeration.ContractDirection;

import java.math.BigDecimal;

/**
 * @author Forest Wang
 * @package xyz.felh.okx.v5.entity.rest.trading.grid
 * @class GridAlgoOrder
 * @email forestwanglin@gmail.cn
 * @date 2024/7/25
 */
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridApiParam implements IRestEntity, IOkxRestRsp {

    /**
     * Instrument ID
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * 币Algo order type
     * grid: Spot grid
     */
    @JSONField(name = "algoOrdType")
    @JsonProperty("algoOrdType")
    private AlgoOrderType algoOrdType;

    /**
     * 回测周期
     * 7D：7天，30D：30天，180D：180天
     */
    @JSONField(name = "duration")
    @JsonProperty("duration")
    private String duration;

    /**
     * Upper price of price range
     */
    @JSONField(name = "maxPx")
    @JsonProperty("maxPx")
    private BigDecimal maxPx;

    /**
     * Lower price of price range
     */
    @JSONField(name = "minPx")
    @JsonProperty("minPx")
    private BigDecimal minPx;

    /**
     * Grid quantity
     */
    @JSONField(name = "gridNum")
    @JsonProperty("gridNum")
    private BigDecimal gridNum;

    /**
     * 单网格最高利润率
     */
    @JSONField(name = "perMaxProfitRate")
    @JsonProperty("perMaxProfitRate")
    private BigDecimal perMaxProfitRate;

    /**
     * 单网格最低利润率
     */
    @JSONField(name = "perMinProfitRate")
    @JsonProperty("perMinProfitRate")
    private BigDecimal perMinProfitRate;

    /**
     * 网格年化收益率
     */
    @JSONField(name = "annualizedRate")
    @JsonProperty("annualizedRate")
    private BigDecimal annualizedRate;

    /**
     * 最小投资数量
     */
    @JSONField(name = "minInvestment")
    @JsonProperty("minInvestment")
    private BigDecimal minInvestment;

    /**
     * 投资币种
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 网格类型
     * 1：等差，2：等比
     */
    @JSONField(name = "runType")
    @JsonProperty("runType")
    private String runType;

    /**
     * 合约网格类型
     * 仅适用于合约网格
     */
    @JSONField(name = "direction")
    @JsonProperty("direction")
    private ContractDirection direction;

    /**
     * 杠杆倍数
     */
    @JSONField(name = "lever")
    @JsonProperty("lever")
    private Integer lever;

}
