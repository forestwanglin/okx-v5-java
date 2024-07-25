package xyz.felh.okx.v5;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import xyz.felh.okx.v5.entity.rest.OkxRestResponse;
import xyz.felh.okx.v5.entity.rest.account.Balance;
import xyz.felh.okx.v5.entity.rest.trading.RsiBackTesting;
import xyz.felh.okx.v5.entity.rest.trading.grid.*;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.CancelOrderReq;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.CancelOrderRsp;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.PlaceOrderReq;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.PlaceOrderRsp;
import xyz.felh.okx.v5.enumeration.AlgoOrderType;
import xyz.felh.okx.v5.enumeration.ContractDirection;
import xyz.felh.okx.v5.enumeration.InstrumentType;
import xyz.felh.okx.v5.enumeration.TriggerCondition;

/**
 * Retrofit2 API interface
 */
public interface OkxApi {

    /**
     * 查看账户余额
     * 限速：10次/2s
     * 限速规则：UserID
     *
     * @param ccy 币种，如 BTC
     *            支持多币种查询（不超过20个），币种之间半角逗号分隔
     * @return account balance list
     */
    @GET("/api/v5/account/balance")
    Single<OkxRestResponse<Balance>> getBalance(@Query("ccy") String ccy);

    /**
     * 下单
     * 限速：60次/2s
     * 跟单交易带单产品的限速：4次/2s
     * 限速规则（期权以外）：UserID + Instrument ID
     * 限速规则（只限期权）：UserID + Instrument Family
     *
     * @param request request
     * @return trade order response
     */
    @POST("/api/v5/trade/order")
    Single<OkxRestResponse<PlaceOrderRsp>> placeOrder(@Body PlaceOrderReq request);

    /**
     * 撤单
     * 撤销之前下的未完成订单。
     * <p>
     * 限速：60次/2s
     * 限速规则（期权以外）：UserID + Instrument ID
     * 限速规则（只限期权）：UserID + Instrument Family
     *
     * @param request request
     * @return CancelOrderRsp
     */
    @POST("/api/v5/trade/cancel-order")
    Single<OkxRestResponse<CancelOrderRsp>> cancelOrder(@Body CancelOrderReq request);

    // Grid trading start

    /**
     * 网格策略委托下单
     * <p>
     * 限速：20次/2s
     * 限速规则：UserID + Instrument ID
     *
     * @param request request
     * @return PlaceGridAlgoOrderRsp
     */
    @POST("/api/v5/tradingBot/grid/order-algo")
    Single<OkxRestResponse<PlaceGridAlgoOrderRsp>> placeGridAlgoOrder(@Body PlaceGridAlgoOrderReq request);

    /**
     * 修改网格策略订单
     * <p>
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param request request
     * @return AmendGridAlgoOrderRsp
     */
    @POST("/api/v5/tradingBot/grid/amend-order-algo")
    Single<OkxRestResponse<AmendGridAlgoOrderRsp>> amendGridAlgoOrder(@Body AmendGridAlgoOrderReq request);

    /**
     * 网格策略停止
     * 每次最多可以撤销10个网格策略。
     * <p>
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param request request
     * @return StopGridAlgoOrderRsp
     */
    @POST("/api/v5/tradingBot/grid/stop-order-algo")
    Single<OkxRestResponse<StopGridAlgoOrderRsp>> stopGridAlgoOrder(@Body StopGridAlgoOrderReq request);

    /**
     * 合约网格平仓
     * 只有处于已停止未平仓状态合约网格可使用该接口
     * <p>
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param request request
     * @return ClosePositionForContractGridRsp
     */
    @POST("/api/v5/tradingBot/grid/close-position")
    Single<OkxRestResponse<ClosePositionForContractGridRsp>> closePositionForContractGrid(@Body ClosePositionForContractGridReq request);

    /**
     * 撤销合约网格平仓单
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param request request
     * @return CancelClosePositionOrderForContractGridRsp
     */
    @POST("/api/v5/tradingBot/grid/cancel-close-order")
    Single<OkxRestResponse<CancelClosePositionOrderForContractGridRsp>> cancelClosePositionForContractGrid(@Body CancelClosePositionOrderForContractGridReq request);

    /**
     * 网格策略立即触发
     * <p>
     * 限速：20次/2s
     * 限速规则：UserID + Instrument ID
     *
     * @param request request
     * @return InstantTriggerGridAlgoOrderRsp
     */
    @POST("/api/v5/tradingBot/grid/order-instant-trigger")
    Single<OkxRestResponse<InstantTriggerGridAlgoOrderRsp>> instantTriggerGridAlgoOrder(@Body InstantTriggerGridAlgoOrderReq request);

    /**
     * 获取未完成网格策略委托单列表
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param algoOrdType 策略订单类型
     *                    grid：现货网格委托
     *                    contract_grid：合约网格委托
     * @param algoId      策略订单ID
     * @param instId      产品ID，如BTC-USDT
     * @param instType    产品类型
     *                    SPOT：币币
     *                    MARGIN：杠杆
     *                    FUTURES：交割合约
     *                    SWAP：永续合约
     * @param after       请求此ID之前（更旧的数据）的分页内容，传的值为对应接口的algoId
     * @param before      请求此ID之后（更新的数据）的分页内容，传的值为对应接口的algoId
     * @param limit       返回结果的数量，最大为100，默认100条
     * @return GridAlgoOrder
     */
    @GET("/api/v5/tradingBot/grid/orders-algo-pending")
    Single<OkxRestResponse<GridAlgoOrder>> getGridAlgoOrderList(
            @Query("algoOrdType") AlgoOrderType algoOrdType,
            @Query("algoId") String algoId,
            @Query("instId") String instId,
            @Query("instType") InstrumentType instType,
            @Query("after") String after,
            @Query("before") String before,
            @Query("limit") Integer limit
    );

    /**
     * 获取历史网格策略委托单列表
     * <p>
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param algoOrdType 策略订单类型
     *                    grid：现货网格委托
     *                    contract_grid：合约网格委托
     * @param algoId      策略订单ID
     * @param instId      产品ID，如BTC-USDT
     * @param instType    产品类型
     *                    SPOT：币币
     *                    MARGIN：杠杆
     *                    FUTURES：交割合约
     *                    SWAP：永续合约
     * @param after       请求此ID之前（更旧的数据）的分页内容，传的值为对应接口的algoId
     * @param before      请求此ID之后（更新的数据）的分页内容，传的值为对应接口的algoId
     * @param limit       返回结果的数量，最大为100，默认100条
     * @return GridAlgoOrder
     */
    @GET("/api/v5/tradingBot/grid/orders-algo-history")
    Single<OkxRestResponse<GridAlgoOrder>> getGridAlgoOrderHistory(
            @Query("algoOrdType") AlgoOrderType algoOrdType,
            @Query("algoId") String algoId,
            @Query("instId") String instId,
            @Query("instType") InstrumentType instType,
            @Query("after") String after,
            @Query("before") String before,
            @Query("limit") Integer limit);

    /**
     * 获取网格策略委托订单详情
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param algoOrdType 策略订单类型
     *                    grid：现货网格委托
     *                    contract_grid：合约网格委托
     * @param algoId      策略订单ID
     * @return GridAlgoOrder
     */
    @GET("/api/v5/tradingBot/grid/orders-algo-details")
    Single<OkxRestResponse<GridAlgoOrder>> getGridAlgoOrderDetails(
            @Query("algoOrdType") AlgoOrderType algoOrdType,
            @Query("algoId") String algoId);

    /**
     * 获取网格策略委托子订单信息
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param algoOrdType 策略订单类型
     *                    grid：现货网格委托
     *                    contract_grid：合约网格委托
     * @param algoId      策略订单ID
     * @param type        子订单状态
     *                    live：未成交
     *                    filled：已成交
     * @param groupId     组ID
     * @param after       请求此ID之前（更旧的数据）的分页内容，传的值为对应接口的ordId
     * @param before      请求此ID之后（更新的数据）的分页内容，传的值为对应接口的ordId
     * @param limit       返回结果的数量，最大为100，默认100条
     * @return GridAlgoSubOrder
     */
    @GET("/api/v5/tradingBot/grid/sub-orders")
    Single<OkxRestResponse<GridAlgoSubOrder>> getGridAlgoSubOrders(
            @Query("algoOrdType") AlgoOrderType algoOrdType,
            @Query("algoId") String algoId,
            @Query("type") String type,
            @Query("groupId") String groupId,
            @Query("after") String after,
            @Query("before") String before,
            @Query("limit") Integer limit);

    /**
     * 获取网格策略委托持仓
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param algoOrdType 订单类型
     *                    contract_grid：合约网格委托
     * @param algoId      策略订单ID
     * @return GridAlgoOrderPosition
     */
    @GET("/api/v5/tradingBot/grid/positions")
    Single<OkxRestResponse<GridAlgoOrderPosition>> getGridAlgoOrderPositions(
            @Query("algoOrdType") AlgoOrderType algoOrdType,
            @Query("algoId") String algoId);

    /**
     * 现货网格提取利润
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param request request
     * @return SpotGridWithdrawIncomeRsp
     */
    @POST("/api/v5/tradingBot/grid/withdraw-income")
    Single<OkxRestResponse<SpotGridWithdrawIncomeRsp>> spotGridWithdrawIncome(@Body SpotGridWithdrawIncomeReq request);

    /**
     * 调整保证金计算
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param request request
     * @return ComputeGridMarginBalanceRsp
     */
    @POST("/api/v5/tradingBot/grid/compute-margin-balance")
    Single<OkxRestResponse<ComputeGridMarginBalanceRsp>> computeGridMarginBalance(@Body ComputeGridMarginBalanceReq request);

    /**
     * 调整保证金
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param request request
     * @return AdjustGridMarginBalanceRsp
     */
    @POST("/api/v5/tradingBot/grid/margin-balance")
    Single<OkxRestResponse<AdjustGridMarginBalanceRsp>> adjustGridMarginBalance(@Body AdjustGridMarginBalanceReq request);

    /**
     * 加仓
     * 该接口用于加仓，仅适用于合约网格。
     * <p>
     * 限速：20次/2s
     * 限速规则：UserID
     *
     * @param request request
     * @return AddGridInvestmentRsp
     */
    @POST("/api/v5/tradingBot/grid/adjust-investment")
    Single<OkxRestResponse<AddGridInvestmentRsp>> addGridInvestment(@Body AddGridInvestmentReq request);

    /**
     * 网格策略智能回测（公共）
     * 公共接口无须鉴权
     * <p>
     * 限速：20次/2s
     * 限速规则：IP
     *
     * @param algoOrdType 策略订单类型
     *                    grid：现货网格委托
     *                    contract_grid：合约网格委托
     * @param instId      产品ID，如BTC-USDT
     * @param direction   合约网格类型
     *                    long：做多，short：做空，neutral：中性
     *                    合约网格必填
     * @param duration    回测周期
     *                    7D：7天，30D：30天，180D：180天
     *                    默认现货网格为7D
     *                    合约网格只支持7D
     * @return GridApiParam
     */
    @GET("/api/v5/tradingBot/grid/ai-param")
    Single<OkxRestResponse<GridApiParam>> getGridAiParameterPublic(
            @Query("algoOrdType") AlgoOrderType algoOrdType,
            @Query("instId") String instId,
            @Query("direction") ContractDirection direction,
            @Query("duration") String duration);

    /**
     * 计算最小投资数量（公共）
     * 公共接口无须鉴权
     * <p>
     * 限速：20次/2s
     * 限速规则：IP
     *
     * @param request request
     * @return ComputeGridMinInvestmentRsp
     */
    @POST("/api/v5/tradingBot/grid/min-investment")
    Single<OkxRestResponse<ComputeGridMinInvestmentRsp>> computeGridMinInvestmentPublic(@Body ComputeGridMinInvestmentReq request);

    /**
     * RSI回测（公共）
     * 公共接口无须鉴权
     * <p>
     * 限速：20次/2s
     * 限速规则：IP
     *
     * @param instId      产品ID，如BTC-USDT
     *                    适用于币币
     * @param timeframe   K线种类
     *                    3m, 5m, 15m, 30m (m代表分钟)
     *                    1H, 4H (H代表小时)
     *                    1D (D代表天)
     * @param thold       阈值
     *                    取值[1,100]的整数
     * @param timePeriod  周期
     *                    14
     * @param triggerCond 触发条件
     *                    cross_up：上穿
     *                    cross_down：下穿
     *                    above：上方
     *                    below：下方
     *                    cross：交叉
     *                    默认是cross_down
     * @param duration    回测周期
     *                    1M：1个月
     *                    默认1M
     * @return RsiBackTesting
     */
    @GET("/api/v5/tradingBot/public/rsi-back-testing")
    Single<OkxRestResponse<RsiBackTesting>> getRsiBackTestingPublic(
            @Query("instId") String instId,
            @Query("timeframe") String timeframe,
            @Query("thold") Integer thold,
            @Query("timePeriod") Integer timePeriod,
            @Query("triggerCond") TriggerCondition triggerCond,
            @Query("duration") String duration);


    // Grid trading end

}
