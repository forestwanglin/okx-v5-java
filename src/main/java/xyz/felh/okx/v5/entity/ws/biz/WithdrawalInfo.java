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
import java.util.List;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class WithdrawalInfo implements WsSubscribeEntity {

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
     * 币种
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
     * 是否为不可交易资产
     * true：不可交易资产，false：可交易资产
     */
    @JSONField(name = "nonTradableAsset")
    @JsonProperty("nonTradableAsset")
    private Boolean nonTradableAsset;

    /**
     * 数量
     */
    @JSONField(name = "amt")
    @JsonProperty("amt")
    private BigDecimal amt;

    /**
     * 提币申请时间，Unix 时间戳的毫秒数格式，如 1655251200000
     */
    @JSONField(name = "ts")
    @JsonProperty("ts")
    private Long ts;

    /**
     * 提币账户
     * 可以是邮箱/手机号/子账户名
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
     * 收币地址
     */
    @JSONField(name = "to")
    @JsonProperty("to")
    private String to;

    /**
     * 如果to为手机号，该字段为该手机号的区号
     */
    @JSONField(name = "areaCodeTo")
    @JsonProperty("areaCodeTo")
    private String areaCodeTo;

    /**
     * 部分币种提币需要标签
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;

    /**
     * 部分币种提币需要此字段（payment_id）
     */
    @JSONField(name = "pmtId")
    @JsonProperty("pmtId")
    private String pmtId;

    /**
     * 部分币种提币需要此字段
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    private String memo;

    /**
     * 提币地址备注。如币种TONCOIN的提币地址备注标签名为comment,则该字段返回：{'comment':'123456'}
     */
    @JSONField(name = "addrEx")
    @JsonProperty("addrEx")
    private Object addrEx;

    /**
     * 提币哈希记录
     * 内部转账该字段返回""
     */
    @JSONField(name = "txId")
    @JsonProperty("txId")
    private String txId;

    /**
     * 提币手续费数量
     */
    @JSONField(name = "fee")
    @JsonProperty("fee")
    private BigDecimal fee;

    /**
     * 提币手续费币种，如 USDT
     * > state	String
     */
    @JSONField(name = "feeCcy")
    @JsonProperty("feeCcy")
    private String feeCcy;

    /**
     * 提币状态
     * <p>
     * 阶段1：等待提币
     * 10：等待划转
     * 0：等待提币
     * 4/5/6/8/9/12：等待客服审核
     * 7：审核通过
     * <p>
     * 阶段2：提币处理中（适用于链上提币，内部转账无此阶段）
     * 1：正在将您的交易广播到链上
     * 15：交易待确认
     * 16：根据当地法律法规，您的提币最多可能需要 24 小时才能到账
     * -3：撤销中
     * <p>
     * 最终阶段
     * -2：已撤销
     * -1：失败
     * 2：提币成功
     */
    @JSONField(name = "state")
    @JsonProperty("state")
    private Integer state;

    /**
     * 提币申请ID
     */
    @JSONField(name = "wdId")
    @JsonProperty("wdId")
    private String wdId;

    /**
     * 客户自定义ID
     */
    @JSONField(name = "clientId")
    @JsonProperty("clientId")
    private String clientId;
}
