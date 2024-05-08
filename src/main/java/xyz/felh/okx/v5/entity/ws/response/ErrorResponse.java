package xyz.felh.okx.v5.entity.ws.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ErrorResponse extends CommonResponse {

}
