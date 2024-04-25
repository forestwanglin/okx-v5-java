package xyz.felh.okx.v5;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import xyz.felh.okx.v5.entity.rest.OkxRestResponse;
import xyz.felh.okx.v5.entity.rest.account.Balance;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.CancelOrderReq;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.CancelOrderRsp;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.PlaceOrderReq;
import xyz.felh.okx.v5.entity.rest.trading.order.booking.PlaceOrderRsp;

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

}
