package xyz.felh.okx.v5.entity.ws.response;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.entity.ws.biz.IndexCandlesticks;
import xyz.felh.okx.v5.entity.ws.biz.MarkPriceCandlesticks;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

@Slf4j
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WsSubscribeResponse<T, D> implements IWsResponse {

    @JSONField(name = "arg")
    @JsonProperty("arg")
    private T arg;

    @JSONField(name = "data")
    @JsonProperty("data")
    private List<D> data;

    public WsSubscribeResponse<T, D> tryParse(Class<T> tClass, Class<D> dClass, String message) {
        try {
            WsSubscribeResponse<JSONObject, ?> response = JSONObject.parseObject(message, new TypeReference<>() {
            });
            if (response != null && response.getData() != null && response.getArg() != null) {
                WsSubscribeResponse<T, D> result = new WsSubscribeResponse<>();
                result.setArg(JSONObject.parseObject(response.getArg().toString(), tClass));

                if (dClass == MarkPriceCandlesticks.class || dClass == IndexCandlesticks.class) {
                    result.setData(response.getData().stream().map(it -> {
                        D instance = null;
                        try {
                            JSONArray array = (JSONArray) it;
                            instance = dClass.getDeclaredConstructor().newInstance();
                            Field field = dClass.getDeclaredField("ts");
                            field.setAccessible(true);
                            field.set(instance, array.getLong(0));
                            field = dClass.getDeclaredField("o");
                            field.setAccessible(true);
                            field.set(instance, array.getBigDecimal(1));
                            field = dClass.getDeclaredField("h");
                            field.setAccessible(true);
                            field.set(instance, array.getBigDecimal(2));
                            field = dClass.getDeclaredField("l");
                            field.setAccessible(true);
                            field.set(instance, array.getBigDecimal(3));
                            field = dClass.getDeclaredField("c");
                            field.setAccessible(true);
                            field.set(instance, array.getBigDecimal(4));
                            field = dClass.getDeclaredField("confirm");
                            field.setAccessible(true);
                            field.set(instance, array.getIntValue(5) == 1);
                        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                                 InvocationTargetException | NoSuchFieldException e) {
                            log.error("new instance error", e);
                        }
                        return instance;
                    }).filter(Objects::nonNull).toList());
                } else {
                    result.setData(JSONArray.parseArray(response.getData().toString(), dClass));
                }
                return result;
            }
        } catch (Exception ex) {
            log.error("tryParse error", ex);
        }
        return null;
    }

}
