package management.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class InfoAboutFootballerCommandDto {
    private Long commandId;
    private String commandName;
    private Integer commandCommission;
    private Long commandBudget;
}
