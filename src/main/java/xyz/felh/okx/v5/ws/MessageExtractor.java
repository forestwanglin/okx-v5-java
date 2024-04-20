package xyz.felh.okx.v5.ws;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.enumeration.Channel;
import xyz.felh.okx.v5.entity.ws.pri.Account;
import xyz.felh.okx.v5.entity.ws.pri.Positions;
import xyz.felh.okx.v5.entity.ws.pub.Instruments;
import xyz.felh.okx.v5.entity.ws.pub.OpenInterest;
import xyz.felh.okx.v5.entity.ws.response.*;
import xyz.felh.okx.v5.entity.ws.response.pri.AccountArg;
import xyz.felh.okx.v5.entity.ws.response.pri.PositionsArg;
import xyz.felh.okx.v5.entity.ws.response.pub.InstrumentsArg;
import xyz.felh.okx.v5.entity.ws.response.pub.OpenInterestArg;

@Slf4j
public class MessageExtractor {

    private static final String EVENT = "event";
    private static final String ARG = "arg";
    private static final String CHANNEL = "channel";

    public static IWsResponse extract(String message) {
        if (new HeartbeatResponse().ofInstance(message)) {
            log.debug("WebSocket heartbeat response {}", message);
            return HeartbeatResponse.builder().message(message).build();
        } else if (new WsResponse<>().ofInstance(message)) {
            if (Event.fromValue(JSON.parseObject(message).getString(EVENT)) == Event.ERROR
                    || Event.fromValue(JSON.parseObject(message).getString(EVENT)) == Event.LOGIN) {
                return JSON.parseObject(message, new TypeReference<WsResponse<?>>() {
                });
            }
            Channel channel = Channel.fromValue(JSONObject.parseObject(message).getJSONObject(ARG).getString(CHANNEL));
            assert channel != null;
            if (channel == Channel.ACCOUNT) {
                return JSON.parseObject(message, new TypeReference<WsResponse<AccountArg>>() {
                });
            } else if (channel == Channel.POSITIONS) {
                return JSON.parseObject(message, new TypeReference<WsResponse<PositionsArg>>() {
                });
            } else if (channel == Channel.INSTRUMENTS) {
                return JSON.parseObject(message, new TypeReference<WsResponse<InstrumentsArg>>() {
                });
            } else if (channel == Channel.OPEN_INTEREST) {
                return JSON.parseObject(message, new TypeReference<WsResponse<OpenInterestArg>>() {
                });
            }
        } else if (new WsSubscribeResponse<>().ofInstance(message)) {
            Channel channel = Channel.fromValue(JSONObject.parseObject(message).getJSONObject(ARG).getString(CHANNEL));
            assert channel != null;
            if (channel == Channel.ACCOUNT) {
                return JSON.parseObject(message, new TypeReference<WsSubscribeResponse<AccountArg, Account>>() {
                });
            } else if (channel == Channel.POSITIONS) {
                return JSON.parseObject(message, new TypeReference<WsSubscribeResponse<PositionsArg, Positions>>() {
                });
            } else if (channel == Channel.INSTRUMENTS) {
                return JSON.parseObject(message, new TypeReference<WsSubscribeResponse<InstrumentsArg, Instruments>>() {
                });
            } else if (channel == Channel.OPEN_INTEREST) {
                return JSON.parseObject(message, new TypeReference<WsSubscribeResponse<OpenInterestArg, OpenInterest>>() {
                });
            }
        }
        return null;
    }

}
