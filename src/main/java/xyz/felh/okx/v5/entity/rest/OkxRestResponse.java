package xyz.felh.okx.v5.entity.rest;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class OkxRestResponse<T extends IOkxRestRsp> implements IOkxRestObject {

    /**
     * 结果代码，0表示成功
     */
    @JSONField(name = "code")
    @JsonProperty("code")
    private Integer code;

    /**
     * 错误信息，代码为0时，该字段为空
     */
    @JSONField(name = "msg")
    @JsonProperty("msg")
    private String msg;

    @JSONField(name = "data")
    @JsonProperty("data")
    private List<T> data;

    /**
     * REST网关接收请求时的时间戳，Unix时间戳的微秒数格式，如 1597026383085123
     * 返回的时间是请求验证后的时间。
     */
    @JSONField(name = "inTime")
    @JsonProperty("inTime")
    private Long inTime;

    /**
     * REST网关发送响应时的时间戳，Unix时间戳的微秒数格式，如 1597026383085123
     */
    @JSONField(name = "outTime")
    @JsonProperty("outTime")
    private Long outTime;

}
