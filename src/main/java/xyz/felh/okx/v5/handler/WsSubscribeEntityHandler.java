package xyz.felh.okx.v5.handler;

import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.WsMessageListener;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;
import xyz.felh.okx.v5.entity.ws.response.Event;
import xyz.felh.okx.v5.entity.ws.response.WsResponse;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;
import xyz.felh.okx.v5.entity.ws.response.WsSubscribeResponse;
import xyz.felh.okx.v5.enumeration.ws.Channel;
import xyz.felh.okx.v5.enumeration.ws.WsChannel;
import xyz.felh.okx.v5.ws.SubscribeStateService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class WsSubscribeEntityHandler<R extends WsRequestArg, T extends WsResponseArg, D extends WsSubscribeEntity> {

    private final Channel channel;
    private final WsResponse<R> response;
    private final WsSubscribeResponse<T, D> subscribeResponse;
    private final WsChannel wsChannel;
    private final SubscribeStateService subscribeStateService;

    public WsSubscribeEntityHandler(Class<R> rClass,
                                    Class<T> tClass,
                                    Class<D> dClass,
                                    Channel channel,
                                    String message,
                                    WsChannel wsChannel,
                                    SubscribeStateService subscribeStateService) {
        this.channel = channel;
        this.response = new WsResponse<R>().tryParse(rClass, message);
        this.subscribeResponse = new WsSubscribeResponse<T, D>().tryParse(tClass, dClass, message);
        this.wsChannel = wsChannel;
        this.subscribeStateService = subscribeStateService;
    }

    public void handle(WsMessageListener wsMessageListener) {
        try {
            if (response != null) {
                Method method = WsMessageListener.class.getDeclaredMethod(channel.getOptCallbackMethodName(), WsResponse.class);
                method.setAccessible(true);
                method.invoke(wsMessageListener, response);
                if (response.getEvent() == Event.SUBSCRIBE) {
                    subscribeStateService.confirmSubscribed(wsChannel, response.getArg());
                } else if (response.getEvent() == Event.UNSUBSCRIBE) {
                    subscribeStateService.removeSubscribed(wsChannel, response.getArg());
                }
            }
            if (subscribeResponse != null) {
                Method method = WsMessageListener.class.getDeclaredMethod(channel.getRcvCallbackMethodName(), WsSubscribeResponse.class);
                method.setAccessible(true);
                method.invoke(wsMessageListener, subscribeResponse);
            }
        } catch (NoSuchMethodException e) {
            log.error("handle error", e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
