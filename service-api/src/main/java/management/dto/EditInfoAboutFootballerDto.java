package management.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class EditInfoAboutFootballerDto {
    private String firstName;
    private String lastName;
    private Integer experience;
    private Integer age;
}
