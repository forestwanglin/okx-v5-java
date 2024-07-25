package xyz.felh.okx.v5.entity.rest.trading.grid;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.felh.okx.v5.entity.rest.IOkxRestRsp;
import xyz.felh.okx.v5.entity.rest.IRestEntity;
import xyz.felh.okx.v5.enumeration.*;
import xyz.felh.okx.v5.enumeration.ws.TdMode;

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
public class GridAlgoSubOrder implements IRestEntity, IOkxRestRsp {

    /**
     * Algo ID
     */
    @JSONField(name = "algoId")
    @JsonProperty("algoId")
    private String algoId;

    /**
     * Client-supplied Algo ID
     */
    @JSONField(name = "algoClOrdId")
    @JsonProperty("algoClOrdId")
    private String algoClOrdId;

    /**
     * Instrument type
     */
    @JSONField(name = "instType")
    @JsonProperty("instType")
    private InstrumentType instType;

    /**
     * Instrument ID
     */
    @JSONField(name = "instId")
    @JsonProperty("instId")
    private String instId;

    /**
     * Algo order created time, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "cTime")
    @JsonProperty("cTime")
    private Long cTime;

    /**
     * Algo order updated time, Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "uTime")
    @JsonProperty("uTime")
    private Long uTime;

    /**
     * 币Algo order type
     * grid: Spot grid
     */
    @JSONField(name = "algoOrdType")
    @JsonProperty("algoOrdType")
    private AlgoOrderType algoOrdType;

    /**
     * 组ID
     */
    @JSONField(name = "groupId")
    @JsonProperty("groupId")
    private String groupId;

    /**
     * 子订单ID
     */
    @JSONField(name = "ordId")
    @JsonProperty("ordId")
    private String ordId;

    /**
     * 子订单交易模式
     * cross：全仓
     * isolated：逐仓
     * cash：非保证金
     */
    @JSONField(name = "tdMode")
    @JsonProperty("tdMode")
    private TdMode tdMode;

    /**
     * 保证金币种
     * 仅适用于单币种保证金模式下的全仓杠杆订单
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 子订单类型
     * market：市价单
     * limit：限价单
     * ioc：立即成交并取消剩余
     */
    @JSONField(name = "ordType")
    @JsonProperty("ordType")
    private OrderType ordType;

    /**
     * Lower price of price range
     */
    @JSONField(name = "minPx")
    @JsonProperty("minPx")
    private BigDecimal minPx;

    /**
     * 子订单委托数量
     */
    @JSONField(name = "sz")
    @JsonProperty("sz")
    private BigDecimal sz;

    /**
     * 子订单状态
     * canceled：撤单成功
     * live：等待成交
     * partially_filled：部分成交
     * filled：完全成交
     * cancelling：撤单中
     */
    @JSONField(name = "state")
    @JsonProperty("state")
    private OrderState state;

    /**
     * 子订单订单方向
     * buy：买
     * sell：卖
     */
    @JSONField(name = "side")
    @JsonProperty("side")
    private Side side;

    /**
     * 子订单委托价格
     */
    @JSONField(name = "px")
    @JsonProperty("px")
    private BigDecimal px;

    /**
     * 子订单手续费数量
     */
    @JSONField(name = "fee")
    @JsonProperty("fee")
    private BigDecimal fee;

    /**
     * 子订单手续费币种
     */
    @JSONField(name = "feeCcy")
    @JsonProperty("feeCcy")
    private String feeCcy;

    /**
     * 子订单返佣数量
     */
    @JSONField(name = "rebate")
    @JsonProperty("rebate")
    private BigDecimal rebate;

    /**
     * 子订单返佣币种
     */
    @JSONField(name = "rebateCcy")
    @JsonProperty("rebateCcy")
    private String rebateCcy;

    /**
     * 子订单平均成交价格
     */
    @JSONField(name = "avgPx")
    @JsonProperty("avgPx")
    private BigDecimal avgPx;

    /**
     * 子订单累计成交数量
     */
    @JSONField(name = "accFillSz")
    @JsonProperty("accFillSz")
    private BigDecimal accFillSz;

    /**
     * 子订单持仓方向
     * net：买卖模式
     */
    @JSONField(name = "posSide")
    @JsonProperty("posSide")
    private PositionsSide posSide;

    /**
     * 子订单收益
     */
    @JSONField(name = "pnl")
    @JsonProperty("pnl")
    private BigDecimal pnl;

    /**
     * 合约面值
     * 仅支持FUTURES/SWAP
     */
    @JSONField(name = "ctVal")
    @JsonProperty("ctVal")
    private BigDecimal ctVal;

    /**
     * 杠杆倍数
     */
    @JSONField(name = "lever")
    @JsonProperty("lever")
    private Integer lever;

    /**
     * 订单标签
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;

}
