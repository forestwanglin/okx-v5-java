package xyz.felh.okx.v5.handler;

import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.WsMessageListener;
import xyz.felh.okx.v5.entity.ws.WsSubscribeEntity;
import xyz.felh.okx.v5.entity.ws.response.WsResponse;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;
import xyz.felh.okx.v5.entity.ws.response.WsSubscribeResponse;
import xyz.felh.okx.v5.enumeration.Channel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class WsSubscribeEntityHandler<T extends WsResponseArg, D extends WsSubscribeEntity> {

    private final Channel channel;
    private final WsResponse<T> response;
    private final WsSubscribeResponse<T, D> subscribeResponse;

    public WsSubscribeEntityHandler(Class<T> tClass, Class<D> dClass, Channel channel, String message) {
        this.channel = channel;
        this.response = new WsResponse<T>().tryParse(tClass, message);
        this.subscribeResponse = new WsSubscribeResponse<T, D>().tryParse(tClass, dClass, message);
    }

    public void handle(WsMessageListener wsMessageListener) {
        try {
            if (response != null) {
                Method method = WsMessageListener.class.getDeclaredMethod(channel.getOptCallbackMethodName(), WsResponse.class);
                method.setAccessible(true);
                method.invoke(wsMessageListener, response);
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
