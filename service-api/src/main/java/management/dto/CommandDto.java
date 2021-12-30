package management.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CommandDto {
    @NotEmpty
    @NotNull
    private String name;
    @Min(1)
    @Max(10)
    private Integer commission;
    @Min(1)
    private Long budget;
    @NotEmpty
    private List<FootballerDto> footballerDtoList;
}
