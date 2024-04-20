package xyz.felh.okx.v5.entity.ws.request;

import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.WsSubUnsubArg;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class WsRequestArg extends WsSubUnsubArg {



}
