package xyz.felh.okx.v5.entity.ws.request;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class WsOnceRequest<T extends WsRequestArg> extends WsRequest<T> {

    /**
     * Unique identifier of the message
     * Provided by client. It will be returned in response message for identifying the corresponding request.
     * A combination of case-sensitive alphanumerics, all numbers, or all letters of up to 32 characters.
     */
    @NonNull
    @JSONField(name = "id")
    @JsonProperty("id")
    private String id;

    /**
     * Request effective deadline. Unix timestamp format in milliseconds, e.g. 1597026383085
     */
    @JSONField(name = "expTime")
    @JsonProperty("expTime")
    private Long expTime;

}
