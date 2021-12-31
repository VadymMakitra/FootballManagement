package management.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class EditInfoAboutFootballerDto {
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁЇїІіЄєҐґ']*", message = "use only English,Ukrainian or Russian letters")
    @NotEmpty(message = "name must not be empty")
    private String firstName;
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁЇїІіЄєҐґ']*", message = "use only English,Ukrainian or Russian letters")
    @NotEmpty(message = "last name must not be empty")
    private String lastName;
    @Max(40)
    private Integer experience;
    @Min(18)
    @Max(60)
    private Integer age;
    @Min(1)
    private Long commandId;
}
