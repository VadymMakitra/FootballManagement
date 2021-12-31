package management.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class AllInfoAboutFootballerDto {
    private Long id;
    private String fullName;
    @Min(1)
    private Integer experience;
    @Min(1)
    @Max(60)
    private Integer age;
    private InfoAboutFootballerCommandDto infoAboutCommandDto;
}
