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
public class EconomicCalendar implements WsSubscribeEntity {

    /**
     * 事件名
     */
    @JSONField(name = "event")
    @JsonProperty("event")
    private String event;

    /**
     * 国家，地区或实体
     */
    @JSONField(name = "region")
    @JsonProperty("region")
    private String region;

    /**
     * 类别名
     */
    @JSONField(name = "category")
    @JsonProperty("category")
    private String category;

    /**
     * 事件实际值
     */
    @JSONField(name = "actual")
    @JsonProperty("actual")
    private BigDecimal actual;

    /**
     * 当前事件上个周期的最新实际值
     * 若发生数据修正，该字段存储上个周期修正后的实际值
     */
    @JSONField(name = "previous")
    @JsonProperty("previous")
    private BigDecimal previous;

    /**
     * 由权威经济学家共同得出的预测值
     */
    @JSONField(name = "forecast")
    @JsonProperty("forecast")
    private BigDecimal forecast;

    /**
     * 该事件上一周期的初始值
     * 仅在修正发生时有值
     */
    @JSONField(name = "prevInitial")
    @JsonProperty("prevInitial")
    private BigDecimal prevInitial;

    /**
     * actual字段值的预期发布时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    @JSONField(name = "date")
    @JsonProperty("date")
    private Long date;

    /**
     * 当前事件指向的日期
     */
    @JSONField(name = "refDate")
    @JsonProperty("refDate")
    private Long refDate;

    /**
     * 经济日历ID
     */
    @JSONField(name = "calendarId")
    @JsonProperty("calendarId")
    private String calendarId;

    /**
     * 推送时间，Unix 时间戳的毫秒数格式，如 1655251200000
     */
    @JSONField(name = "ts")
    @JsonProperty("ts")
    private Long ts;

    /**
     * 事件实际值对应的单位
     */
    @JSONField(name = "unit")
    @JsonProperty("unit")
    private String unit;

    /**
     * 事件实际值对应的货币
     */
    @JSONField(name = "ccy")
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 重要性
     * 1: 低
     * 2: 中等
     * 3: 高
     */
    @JSONField(name = "importance")
    @JsonProperty("importance")
    private Integer importance;

}
