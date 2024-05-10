package xyz.felh.okx.v5.handler;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.OkxWsApiService;
import xyz.felh.okx.v5.entity.ws.response.LoginResponse;
import xyz.felh.okx.v5.enumeration.ws.WsChannel;

@Slf4j
public class WsLoginHandler implements WsHandler {

    private final WsChannel wsChannel;
    private final LoginResponse loginResponse;

    public WsLoginHandler(WsChannel wsChannel, String message) {
        this.wsChannel = wsChannel;
        this.loginResponse = JSONObject.parseObject(message, LoginResponse.class);
    }

    @Override
    public void handle(OkxWsApiService okxWsApiService) {
        log.debug("wsChannel: {} {}", wsChannel, loginResponse);
        okxWsApiService.setHasLogin(true);
        okxWsApiService.getWsMessageListener().onLoginSuccess();
        okxWsApiService.getSubscribeStateService().restoreSubscribed(WsChannel.PRIVATE);
    }

}
