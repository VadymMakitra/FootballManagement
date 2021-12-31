package management.dto;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class TransferDto {
    @Min(1)
    private Long footballerId;
    @Min(1)
    private Long commandId;
}
