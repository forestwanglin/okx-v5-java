package xyz.felh.okx.v5.entity.ws.biz;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DepositInfo implements WsSubscribeEntity {

    /**
     * (产生数据者的）用户标识
     */
    @JSONField(name = "uid")
    @JsonProperty("uid")
    private String uid;

    /**
     * 子账户名称
     * 如果是母账户产生的数据，该字段返回""
     */
    @JSONField(name = "subAcct")
    @JsonProperty("subAcct")
    private String subAcct;

    /**
     * 推送时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "pTime")
    @JsonProperty("pTime")
    private Long pTime;

    /**
     * 币种名称，如 BTC
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 币种链信息
     * 有的币种下有多个链，必须要做区分，如USDT下有USDT-ERC20，USDT-TRC20多个链
     */
    @JSONField(name = "chain")
    @JsonProperty("chain")
    private String chain;

    /**
     * 充值数量
     */
    @JSONField(name = "amt")
    @JsonProperty("amt")
    private BigDecimal amt;

    /**
     * 充值账户，只显示内部账户转账地址，不显示区块链充值地址
     */
    @JSONField(name = "from")
    @JsonProperty("from")
    private String from;

    /**
     * 如果from为手机号，该字段为该手机号的区号
     */
    @JSONField(name = "areaCodeFrom")
    @JsonProperty("areaCodeFrom")
    private String areaCodeFrom;

    /**
     * 到账地址
     */
    @JSONField(name = "to")
    @JsonProperty("to")
    private String to;

    /**
     * 区块转账哈希记录
     */
    @JSONField(name = "txId")
    @JsonProperty("txId")
    private String txId;

    /**
     * 充值记录创建时间，Unix 时间戳的毫秒数格式，如 1655251200000
     */
    @JSONField(name = "ts")
    @JsonProperty("ts")
    private Long ts;

    /**
     * 账充值状态
     * 0：等待确认
     * 1：确认到账
     * 2：充值成功
     * 8：因该币种暂停充值而未到账，恢复充值后自动到账
     * 11：命中地址黑名单
     * 12：账户或充值被冻结
     * 13：子账户充值拦截
     * 14：KYC限额
     */
    @JSONField(name = "state")
    @JsonProperty("state")
    private Integer state;

    /**
     * 充值记录 ID
     */
    @JSONField(name = "depId")
    @JsonProperty("depId")
    private String depId;

    /**
     * 内部转账发起者提币申请 ID
     * 如果该笔充值来自于内部转账，则该字段展示内部转账发起者的提币申请 ID，其他情况返回""
     */
    @JSONField(name = "fromWdId")
    @JsonProperty("fromWdId")
    private String fromWdId;

    /**
     * 最新的充币网络确认数
     */
    @JSONField(name = "actualDepBlkConfirm")
    @JsonProperty("actualDepBlkConfirm")
    private Integer actualDepBlkConfirm;

}
