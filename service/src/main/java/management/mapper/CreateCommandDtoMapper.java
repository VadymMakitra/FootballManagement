package management.mapper;

import management.dto.CommandDto;
import management.entity.Command;
import management.entity.Footballer;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateCommandDtoMapper extends AbstractConverter<CommandDto, Command> {
    @Override
    protected Command convert(CommandDto commandDto) {
        List<Footballer> footballers = commandDto.getFootballerDtoList().stream()
                .map(footballerDto -> Footballer.builder()
                        .experience(footballerDto.getExperience())
                        .age(footballerDto.getAge())
                        .lastName(footballerDto.getLastName())
                        .firstName(footballerDto.getFirstName())
                        .build())
                .collect(Collectors.toList());
        return Command.builder()
                .budget(commandDto.getBudget())
                .commission(commandDto.getCommission())
                .name(commandDto.getName())
                .footballers(footballers)
                .build();
    }
}
