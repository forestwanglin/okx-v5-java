package xyz.felh.okx.v5.entity.ws.request;

import xyz.felh.okx.v5.enumeration.ws.Channel;

/**
 * The interface of websocket channel argument
 */
public interface WsChannelRequestArg extends WsRequestArg {

    Channel getChannel();

}
