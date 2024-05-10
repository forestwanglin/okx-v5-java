package xyz.felh.okx.v5.handler;

import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.OkxWsApiService;
import xyz.felh.okx.v5.WsMessageListener;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;
import xyz.felh.okx.v5.entity.ws.response.WsSubscribeResponse;
import xyz.felh.okx.v5.enumeration.ws.Channel;
import xyz.felh.okx.v5.enumeration.ws.WsChannel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class WsSubscribePushHandler<T extends WsResponseArg, D extends WsSubscribeEntity> implements WsHandler {

    private final Channel channel;
    private final WsSubscribeResponse<T, D> subscribeResponse;
    private final WsChannel wsChannel;

    public WsSubscribePushHandler(Class<T> tClass,
                                  Class<D> dClass,
                                  Channel channel,
                                  String message,
                                  WsChannel wsChannel) {
        this.channel = channel;
        this.subscribeResponse =  WsSubscribeResponse.tryParse(tClass, dClass, message);
        this.wsChannel = wsChannel;
    }

    @Override
    public void handle(OkxWsApiService okxWsApiService) {
        log.debug("ws chanel {}", wsChannel);
        try {
            if (subscribeResponse != null && okxWsApiService.getWsMessageListener() != null) {
                Method method = WsMessageListener.class.getDeclaredMethod(channel.getRcvCallbackMethodName(), WsSubscribeResponse.class);
                method.setAccessible(true);
                method.invoke(okxWsApiService.getWsMessageListener(), subscribeResponse);
            }
        } catch (NoSuchMethodException e) {
            log.error("handle error", e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
