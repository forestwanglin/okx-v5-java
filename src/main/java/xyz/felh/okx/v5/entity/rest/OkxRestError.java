package xyz.felh.okx.v5.entity.rest;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OkxRestError implements IOkxRestObject {

    @JSONField(name = "code")
    @JsonProperty("code")
    private Integer code;

    @JSONField(name = "msg")
    @JsonProperty("msg")
    private String msg;

}
