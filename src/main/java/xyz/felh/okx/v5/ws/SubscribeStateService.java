package xyz.felh.okx.v5.ws;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import xyz.felh.okx.v5.OkxWsApiService;
import xyz.felh.okx.v5.constant.OkxConstants;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;
import xyz.felh.okx.v5.entity.ws.request.pri.LoginArg;
import xyz.felh.okx.v5.enumeration.ws.WsChannel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is to handle all subscribed state
 */
@Slf4j
public class SubscribeStateService {

    private final OkxWsApiService okxWsApiService;
    // all subscribed params to resubscribe when re login after exceptional close
    private final Map<WsChannel, List<SubscribeState>> subscribedMap;

    private String apiKey;
    private String passphrase;
    private String secretKey;

    public SubscribeStateService(OkxWsApiService okxWsApiService) {
        this.okxWsApiService = okxWsApiService;
        this.subscribedMap = new HashMap<>();
    }

    /**
     * Save last successfully login information
     *
     * @param apiKey     api key
     * @param passphrase passphrase
     * @param secretKey  secretKey
     */
    public void saveLoginInfo(String apiKey, String passphrase, String secretKey) {
        this.apiKey = apiKey;
        this.passphrase = passphrase;
        this.secretKey = secretKey;
    }

    /**
     * Check if current channel has been subscribed successfully
     *
     * @param wsChannel channel
     * @param arg       arg
     * @return if current channel has been subscribed
     */
    public boolean hasSubscribed(WsChannel wsChannel, WsRequestArg arg) {
        List<SubscribeState> subscribeStates = subscribedMap.get(wsChannel);
        if (subscribeStates != null) {
            for (SubscribeState subscribeState : subscribeStates) {
                if (subscribeState.getArg().toString().equals(arg.toString())
                        && subscribeState.isSubscribed()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Add param when call subscribe
     *
     * @param wsChannel channel
     * @param arg       arg
     */
    public void addSubscribed(WsChannel wsChannel, WsRequestArg arg) {
        List<SubscribeState> subscribeStates = subscribedMap.get(wsChannel);
        if (subscribeStates == null) {
            subscribeStates = new ArrayList<>();
        }
        subscribeStates.add(SubscribeState.builder()
                .arg(arg)
                .subscribed(false)
                .build());
        subscribedMap.put(wsChannel, subscribeStates);
    }

    /**
     * confirm subscribed
     *
     * @param wsChannel channel
     * @param arg       arg
     */
    public void confirmSubscribed(WsChannel wsChannel, WsRequestArg arg) {
        List<SubscribeState> subscribeStates = subscribedMap.get(wsChannel);
        if (subscribeStates != null) {
            for (SubscribeState subscribeState : subscribeStates) {
                if (subscribeState.getArg().equals(arg)) {
                    log.info("Confirm subscribed to channel {} {} {}", wsChannel, arg, subscribeState.getArg());
                    subscribeState.setSubscribed(true);
                }
            }
        }
    }

    /**
     * remove subscribed data
     *
     * @param wsChannel channel
     * @param arg       arg
     */
    public void removeSubscribed(WsChannel wsChannel, WsRequestArg arg) {
        List<SubscribeState> subscribeStates = subscribedMap.get(wsChannel);
        if (subscribeStates != null) {
            for (SubscribeState subscribeState : subscribeStates) {
                if (subscribeState.getArg().equals(arg)) {
                    log.info("remove subscribed to channel {} {} {}", wsChannel, arg, subscribeState.getArg());
                    subscribeStates.remove(subscribeState);
                    break;
                }
            }
        }
    }

    /**
     * try login again
     */
    public void tryLogin() {
        if (apiKey != null && passphrase != null && secretKey != null) {
            log.info("try login");
            okxWsApiService.login(LoginArg.builder()
                    .apiKey(apiKey)
                    .passphrase(passphrase)
                    .timestamp(System.currentTimeMillis() / 1000 + OkxConstants.EMPTY)
                    .build(), secretKey);
        }
    }

    /**
     * 恢复某个channel的订阅
     *
     * @param wsChannel channel
     */
    public void restoreSubscribed(WsChannel wsChannel) {
        List<SubscribeState> subscribeStates = subscribedMap.get(wsChannel);
        if (subscribeStates != null) {
            for (SubscribeState subscribeState : subscribeStates) {
                if (subscribeState.isSubscribed()) {
                    okxWsApiService.pureSubscribe(wsChannel, subscribeState.getArg());
                }
            }
        }
    }

    @Builder
    @Data
    static class SubscribeState {
        private WsRequestArg arg;
        private boolean subscribed;
    }

}
