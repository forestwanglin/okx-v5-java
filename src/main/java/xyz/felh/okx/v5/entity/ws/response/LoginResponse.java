package xyz.felh.okx.v5.entity.ws.response;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class LoginResponse extends CommonResponse {

    @Override
    protected CommonResponse parse(String message) {
        return JSONObject.parseObject(message, ErrorResponse.class);
    }

    @Override
    protected boolean validate() {
        return getEvent() == Event.LOGIN;
    }

}
