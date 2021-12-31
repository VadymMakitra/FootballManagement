package management.service;

import management.ModelUtils;
import management.dto.AllInfoAboutCommandDto;
import management.dto.CommandDto;
import management.entity.Command;
import management.entity.Footballer;
import management.exception.CommandNotFoundException;
import management.repository.CommandRepository;
import management.repository.FootballerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CommandServiceImlTest {
    @InjectMocks
    private CommandServiceImpl commandService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CommandRepository commandRepository;
    @Mock
    private FootballerRepository footballerRepository;

    @Test
    void addCommandTest() {
        CommandDto dto = ModelUtils.getCommandDto();
        Command command = ModelUtils.getCommand();
        List<Footballer> footballers = ModelUtils.getFootballerList();

        Mockito.when(modelMapper.map(dto, Command.class)).thenReturn(command);
        Mockito.when(commandRepository.save(command)).thenReturn(command);
        Mockito.when(footballerRepository.saveAll(command.getFootballers())).thenReturn(footballers);

        commandService.addCommand(dto);

        Mockito.verify(modelMapper).map(dto, Command.class);
        Mockito.verify(commandRepository).save(command);
        Mockito.verify(footballerRepository).saveAll(command.getFootballers());
    }

    @Test
    void getInfoAboutAllCommandTest() {
        List<Command> commandList = ModelUtils.getCommandList();
        Command command = ModelUtils.getCommand();
        AllInfoAboutCommandDto allInfoAboutCommandDto = ModelUtils.getAllInfoAboutCommandDto();
        List<AllInfoAboutCommandDto> allInfoAboutCommandDtos = List.of(allInfoAboutCommandDto);

        when(commandRepository.findAll()).thenReturn(commandList);
        when(modelMapper.map(command, AllInfoAboutCommandDto.class)).thenReturn(allInfoAboutCommandDto);

        assertEquals(allInfoAboutCommandDtos, commandService.getInfoAboutAllCommand());

        verify(commandRepository).findAll();
        verify(modelMapper).map(command, AllInfoAboutCommandDto.class);
    }

    @Test
    void editInfoAboutCommandTest() {
        Command command = ModelUtils.getCommand();
        CommandDto commandDto = ModelUtils.getCommandDto();
        List<Footballer> footballers = ModelUtils.getFootballerList();

        when(commandRepository.findById(anyLong())).thenReturn(Optional.of(command));
        when(commandRepository.save(command)).thenReturn(command);
        when(footballerRepository.saveAll(command.getFootballers())).thenReturn(footballers);

        commandService.editInfoAboutCommand(1L, commandDto);

        verify(commandRepository).findById(anyLong());
        verify(commandRepository).save(command);
        verify(footballerRepository).saveAll(command.getFootballers());
    }

    @Test
    void editInfoAboutCommandThrowsCommandNotFoundExceptionTest() {
        CommandDto commandDto = ModelUtils.getCommandDto();

        when(commandRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(CommandNotFoundException.class, () -> commandService.editInfoAboutCommand(1L, commandDto));

        verify(commandRepository).findById(anyLong());
    }

    @Test
    void deleteCommandByIdTest() {
        Command command = ModelUtils.getCommand();

        when(commandRepository.findById(1L)).thenReturn(Optional.of(command));

        commandService.deleteCommandById(1L);

        verify(commandRepository).findById(1L);
        verify(commandRepository).delete(command);
    }

    @Test
    void deleteCommandByIdThrowsCommandNotFoundExceptionTest() {
        when(commandRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CommandNotFoundException.class, () -> commandService.deleteCommandById(1L));

        verify(commandRepository).findById(1L);
    }

}
