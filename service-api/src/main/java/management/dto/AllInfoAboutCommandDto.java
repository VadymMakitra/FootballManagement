package management.dto;

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class AllInfoAboutCommandDto {
    private Long commandId;
    private String commandName;
    private Integer commandCommission;
    private Long commandBudget;
}
