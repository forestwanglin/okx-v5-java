package xyz.felh.okx.v5.entity.ws.response.pri;

import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;
import xyz.felh.okx.v5.enumeration.Channel;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BalanceAndPositionArg extends WsResponseArg {

    @Builder.Default
    private Channel channel = Channel.BALANCE_AND_POSITION;

}

