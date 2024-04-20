package xyz.felh.okx.v5.entity.ws.request.pri;

import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.request.WsRequestArg;
import xyz.felh.okx.v5.enumeration.Channel;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BalanceAndPositionArg extends WsRequestArg {

    @Builder.Default
    private Channel channel = Channel.BALANCE_AND_POSITION;

}
