package management.service;

import lombok.AllArgsConstructor;
import management.constant.ErrorMessage;
import management.dto.AllInfoAboutCommandDto;
import management.dto.CommandDto;
import management.dto.FootballerDto;
import management.entity.Command;
import management.entity.Footballer;
import management.repository.CommandRepository;
import management.repository.FootballerRepository;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommandServiceImpl implements CommandService {
    private final CommandRepository commandRepository;
    private final FootballerRepository footballerRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addCommand(CommandDto commandDto) {
        Command command = modelMapper.map(commandDto, Command.class);
        command.getFootballers().forEach(footballer -> footballer.setCommand(command));
        commandRepository.save(command);
        footballerRepository.saveAll(command.getFootballers());
    }

    @Override
    public List<AllInfoAboutCommandDto> getInfoAboutAllCommand() {
        return commandRepository.findAll().stream()
                .map(command -> modelMapper.map(command, AllInfoAboutCommandDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void editInfoAboutCommand(Long id, CommandDto dto) {
        Command command = getCommandById(id);

        List<Footballer> footballers = new ArrayList<>();
        footballers.add(buildFootballer(dto, command));

        Command editedCommand = Command.builder()
                .id(command.getId())
                .name(dto.getName() != null ? dto.getName() : command.getName())
                .commission(dto.getCommission() != null ? dto.getCommission() : command.getCommission())
                .budget(dto.getBudget() != null ? dto.getBudget() : command.getBudget())
                .footballers(footballers)
                .build();
        footballers.forEach(footballer -> footballer.setCommand(editedCommand));

        commandRepository.save(editedCommand);
        footballerRepository.saveAll(editedCommand.getFootballers());
    }

    private Footballer buildFootballer(CommandDto commandDto, Command command) {
        Footballer footballer = new Footballer();

        for (FootballerDto dto : commandDto.getFootballerDtoList()) {
            footballer = Footballer.builder()
                    .lastName(dto.getLastName() != null ? dto.getLastName() : getFootballerFromCommand(command).getLastName())
                    .firstName(dto.getFirstName() != null ? dto.getFirstName() : getFootballerFromCommand(command).getFirstName())
                    .age(dto.getAge() != null ? dto.getAge() : getFootballerFromCommand(command).getAge())
                    .id(getFootballerFromCommand(command).getId())
                    .experience(dto.getExperience() != null ? dto.getExperience() : getFootballerFromCommand(command).getExperience())
                    .build();
        }

        return footballer;
    }

    private Footballer getFootballerFromCommand(Command command) {
        Footballer neededFootballer = new Footballer();

        for (Footballer footballer : command.getFootballers()) {
            neededFootballer = footballer;
        }

        return neededFootballer;
    }

    private Command getCommandById(Long id) {
        return commandRepository.findById(id).orElseThrow(() ->
                new CommandAcceptanceException(ErrorMessage.COMMAND_NOT_EXIST));
    }

    @Override
    public void deleteCommandById(Long id) {
        commandRepository.delete(getCommandById(id));
    }
}
