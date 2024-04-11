package xyz.felh.okx.v5.ws;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.entity.ws.Channel;
import xyz.felh.okx.v5.entity.ws.pub.Instruments;
import xyz.felh.okx.v5.entity.ws.pub.InstrumentsArg;
import xyz.felh.okx.v5.entity.ws.pub.OpenInterest;
import xyz.felh.okx.v5.entity.ws.pub.OpenInterestArg;
import xyz.felh.okx.v5.entity.ws.response.HeartbeatResponse;
import xyz.felh.okx.v5.entity.ws.response.IWsResponse;
import xyz.felh.okx.v5.entity.ws.response.WsResponse;
import xyz.felh.okx.v5.entity.ws.response.WsSubscribeResponse;

@Slf4j
public class MessageExtractor {

    public static IWsResponse extract(String message) {
        if (new HeartbeatResponse().ofInstance(message)) {
            log.debug("WebSocket heartbeat response {}", message);
            return HeartbeatResponse.builder().message(message).build();
        } else if (new WsResponse<>().ofInstance(message)) {
            Channel channel = Channel.fromValue(JSONObject.parseObject(message).getJSONObject("arg").getString("channel"));
            assert channel != null;
            switch (channel) {
                case INSTRUMENTS -> {
                    return JSON.parseObject(message, new TypeReference<WsResponse<InstrumentsArg>>() {
                    });
                }
                case OPEN_INTEREST -> {
                    return JSON.parseObject(message, new TypeReference<WsResponse<OpenInterestArg>>() {
                    });
                }
                default -> {
                    return null;
                }
            }
        } else if (new WsSubscribeResponse<>().ofInstance(message)) {
            Channel channel = Channel.fromValue(JSONObject.parseObject(message).getJSONObject("arg").getString("channel"));
            assert channel != null;
            switch (channel) {
                case INSTRUMENTS -> {
                    return JSON.parseObject(message, new TypeReference<WsSubscribeResponse<InstrumentsArg, Instruments>>() {
                    });
                }
                case OPEN_INTEREST -> {
                    return JSON.parseObject(message, new TypeReference<WsSubscribeResponse<OpenInterestArg, OpenInterest>>() {
                    });
                }
                default -> {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

}
