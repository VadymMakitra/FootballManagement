package management.service;

import lombok.AllArgsConstructor;
import management.constant.ErrorMessage;
import management.dto.AllInfoAboutFootballerDto;
import management.dto.EditInfoAboutFootballerDto;
import management.dto.FootballerDto;
import management.entity.Command;
import management.entity.Footballer;
import management.exception.CommandNotFoundException;
import management.exception.FootballerNotFoundException;
import management.repository.CommandRepository;
import management.repository.FootballerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FootballerServiceImpl implements FootballerService {
    private final FootballerRepository footballerRepository;
    private final CommandRepository commandRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addFootballer(FootballerDto dto) {
        Footballer footballer = modelMapper.map(dto, Footballer.class);
        Command command = findCommandById(dto.getCommandId());
        footballer.setCommand(command);
        footballerRepository.save(footballer);
    }

    @Override
    public List<AllInfoAboutFootballerDto> getInfoAboutFootballers() {
        return footballerRepository.findAll().stream()
                .map(infoAboutFootballer -> modelMapper.map(infoAboutFootballer, AllInfoAboutFootballerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void editInfoAboutFootballer(Long id, EditInfoAboutFootballerDto dto) {
        Footballer footballer = findFootballerById(id);
        Command command = findCommandById(dto.getCommandId());

        Footballer editedFootballer = Footballer.builder()
                .id(footballer.getId())
                .firstName(dto.getFirstName() != null ? dto.getFirstName() : footballer.getFirstName())
                .lastName(dto.getLastName() != null ? dto.getLastName() : footballer.getLastName())
                .age(dto.getAge() != null ? dto.getAge() : footballer.getAge())
                .experience(dto.getExperience() != null ? dto.getExperience() : footballer.getExperience())
                .command(dto.getCommandId() != null ? command : footballer.getCommand())
                .build();

        footballerRepository.save(editedFootballer);
    }

    private Footballer findFootballerById(Long id) {
        return footballerRepository.findById(id).orElseThrow(
                () -> new FootballerNotFoundException(ErrorMessage.FOOTBALLER_NOT_EXIST));
    }

    private Command findCommandById(Long id) {
        return commandRepository.findById(id).orElseThrow(() ->
                new CommandNotFoundException(ErrorMessage.COMMAND_NOT_EXIST));
    }

    @Override
    public void deleteFootballerById(Long id) {
        footballerRepository.delete(findFootballerById(id));
    }
}
