package management.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class FootballerDto {
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁЇїІіЄєҐґ']*", message = "use only English,Ukrainian or Russian letters")
    @NotEmpty(message = "first name must not be empty")
    @NotNull
    private String firstName;
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁЇїІіЄєҐґ']*", message = "use only English,Ukrainian or Russian letters")
    @NotEmpty(message = "last name must not be empty")
    @NotNull
    private String lastName;
    @NotNull
    @Max(40)
    private Integer experience;
    @Min(18)
    @Max(60)
    private Integer age;
    @Min(1)
    private Long commandId;
}
