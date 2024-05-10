package xyz.felh.okx.v5.handler;

import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.OkxWsApiService;
import xyz.felh.okx.v5.WsMessageListener;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;
import xyz.felh.okx.v5.entity.ws.response.Event;
import xyz.felh.okx.v5.entity.ws.response.WsResponse;
import xyz.felh.okx.v5.enumeration.ws.Channel;
import xyz.felh.okx.v5.enumeration.ws.WsChannel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class WsSubscribeHandler<R extends WsRequestArg> implements WsHandler {

    private final Channel channel;
    private final WsResponse<R> response;
    private final WsChannel wsChannel;

    public WsSubscribeHandler(Class<R> rClass,
                              Channel channel,
                              String message,
                              WsChannel wsChannel) {
        this.channel = channel;
        this.response = WsResponse.tryParse(rClass, message);
        this.wsChannel = wsChannel;
    }

    @Override
    public void handle(OkxWsApiService okxWsApiService) {
        try {
            if (response != null && okxWsApiService.getWsMessageListener() != null && okxWsApiService.getSubscribeStateService() != null) {
                Method method = WsMessageListener.class.getDeclaredMethod(channel.getOptCallbackMethodName(), WsResponse.class);
                method.setAccessible(true);
                method.invoke(okxWsApiService.getWsMessageListener(), response);
                if (response.getEvent() == Event.SUBSCRIBE) {
                    okxWsApiService.getSubscribeStateService().confirmSubscribed(wsChannel, response.getArg());
                } else if (response.getEvent() == Event.UNSUBSCRIBE) {
                    okxWsApiService.getSubscribeStateService().removeSubscribed(wsChannel, response.getArg());
                }
            }
        } catch (NoSuchMethodException e) {
            log.error("handle error", e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
