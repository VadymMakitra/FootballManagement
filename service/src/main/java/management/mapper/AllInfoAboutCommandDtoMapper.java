package management.mapper;

import management.dto.AllInfoAboutCommandDto;
import management.dto.AllInfoAboutFootballerDto;
import management.entity.Command;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AllInfoAboutCommandDtoMapper extends AbstractConverter<Command, AllInfoAboutCommandDto> {
    @Override
    protected AllInfoAboutCommandDto convert(Command command) {
        return AllInfoAboutCommandDto.builder()
                .commandId(command.getId())
                .commandName(command.getName())
                .commandCommission(command.getCommission())
                .commandBudget(command.getBudget())
                .build();
    }
}
