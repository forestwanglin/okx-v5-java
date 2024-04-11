package xyz.felh.okx.v5.constant;

/**
 * OKX constants
 */
public interface OkxConstants {

    String BASE_URL = "https://www.okx.com";
    String WS_PUBLIC_URL = "wss://ws.okx.com:8443/ws/v5/public";
    String WS_PRIVATE_URL = "wss://ws.okx.com:8443/ws/v5/private";
    String WS_BUSINESS_URL = "wss://ws.okx.com:8443/ws/v5/business";

    String SIM_BASE_URL = "https://www.okx.com";
    String SIM_WS_PUBLIC_URL = "wss://wspap.okx.com:8443/ws/v5/public?brokerId=9999";
    String SIM_WS_PRIVATE_URL = "wss://wspap.okx.com:8443/ws/v5/public?brokerId=9999";
    String SIM_WS_BUSINESS_URL = "wss://wspap.okx.com:8443/ws/v5/business?brokerId=9999";

    /**
     * 字符串类型的APIKey
     */
    String HEADER_OK_ACCESS_KEY = "OK-ACCESS-KEY";
    /**
     * 使用HMAC SHA256哈希函数获得哈希值，再使用Base-64编码（请参阅签名）。
     */
    String HEADER_OK_ACCESS_SIGN = "OK-ACCESS-SIGN";
    /**
     * 发起请求的时间（UTC），如：2020-12-08T09:08:57.715Z
     */
    String HEADER_OK_ACCESS_TIMESTAMP = "OK-ACCESS-TIMESTAMP";
    /**
     * 您在创建API密钥时指定的Passphrase
     */
    String HEADER_OK_ACCESS_PASSPHRASE = "OK-ACCESS-PASSPHRASE";

    /**
     * 模拟盘
     */
    String HEADER_X_SIMULATED_TRADING = "x-simulated-trading";

    /**
     * 发送的心跳信息
     */
    String HEARTBEAT_REQ_MESSAGE = "ping";
    /**
     * 回复的心跳信息
     */
    String HEARTBEAT_RSP_MESSAGE = "pong";
    int HEARTBEAT_INTERVAL_SEC = 25;

}
