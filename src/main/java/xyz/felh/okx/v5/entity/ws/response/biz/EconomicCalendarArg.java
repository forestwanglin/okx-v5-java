package xyz.felh.okx.v5.entity.ws.response.biz;

import lombok.*;
import lombok.experimental.SuperBuilder;
import xyz.felh.okx.v5.entity.ws.response.WsResponseArg;
import xyz.felh.okx.v5.enumeration.ws.Channel;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class EconomicCalendarArg extends WsResponseArg {

    @Builder.Default
    private Channel channel = Channel.ECONOMIC_CALENDAR;

}
