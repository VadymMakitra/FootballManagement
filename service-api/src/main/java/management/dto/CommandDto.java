package management.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CommandDto {
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁЇїІіЄєҐґ']*", message = "use only English,Ukrainian or Russian letters")
    @NotEmpty(message = "name must not be empty")
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
