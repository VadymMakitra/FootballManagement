package management.service;

import management.ModelUtils;
import management.dto.AllInfoAboutFootballerDto;
import management.dto.EditInfoAboutFootballerDto;
import management.dto.FootballerDto;
import management.entity.Command;
import management.entity.Footballer;
import management.exception.CommandNotFoundException;
import management.exception.FootballerNotFoundException;
import management.repository.CommandRepository;
import management.repository.FootballerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FootballerServiceImplTest {
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CommandRepository commandRepository;
    @Mock
    private FootballerRepository footballerRepository;
    @InjectMocks
    FootballerServiceImpl footballerService;

    @Test
    void addFootballerTest() {
        FootballerDto dto = ModelUtils.getFootballerDto();
        Footballer footballer = ModelUtils.getFootballer();
        Command command = ModelUtils.getCommand();

        when(modelMapper.map(dto, Footballer.class)).thenReturn(footballer);
        when(commandRepository.findById(1L)).thenReturn(Optional.of(command));
        when(footballerRepository.save(footballer)).thenReturn(footballer);

        footballerService.addFootballer(dto);

        verify(modelMapper).map(dto, Footballer.class);
        verify(commandRepository).findById(1L);
        verify(footballerRepository).save(footballer);
    }

    @Test
    void getInfoAboutFootballersTest() {
        List<Footballer> footballerList = ModelUtils.getFootballerList();
        AllInfoAboutFootballerDto infoAboutFootballerDto = ModelUtils.getAllInfoAboutFootballerDto().get(0);

        when(footballerRepository.findAll()).thenReturn(footballerList);
        when(modelMapper.map(footballerList.get(0), AllInfoAboutFootballerDto.class)).thenReturn(infoAboutFootballerDto);

        assertEquals(ModelUtils.getAllInfoAboutFootballerDto(), footballerService.getInfoAboutFootballers());

        verify(footballerRepository).findAll();
        verify(modelMapper).map(footballerList.get(0), AllInfoAboutFootballerDto.class);
    }

    @Test
    void editInfoAboutFootballerTest() {
        Footballer footballer = ModelUtils.getFootballer();
        Command command = ModelUtils.getCommand();
        EditInfoAboutFootballerDto dto = ModelUtils.getEditedInfoAboutFootballerDto();

        when(footballerRepository.findById(1L)).thenReturn(Optional.of(footballer));
        when(commandRepository.findById(1L)).thenReturn(Optional.of(command));
        when(footballerRepository.save(footballer)).thenReturn(footballer);

        footballerService.editInfoAboutFootballer(1L, dto);

        verify(footballerRepository).findById(1L);
        verify(commandRepository).findById(1L);
        verify(footballerRepository).save(footballer);
    }

    @Test
    void editInfoAboutFootballerThrowsFootballerNotFoundExceptionTest() {
        EditInfoAboutFootballerDto dto = ModelUtils.getEditedInfoAboutFootballerDto();

        when(footballerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FootballerNotFoundException.class, () -> footballerService.editInfoAboutFootballer(1L, dto));

        verify(footballerRepository).findById(1L);
    }

    @Test
    void editInfoAboutFootballerThrowsCommandNotFoundExceptionTest() {
        Footballer footballer = ModelUtils.getFootballer();
        EditInfoAboutFootballerDto dto = ModelUtils.getEditedInfoAboutFootballerDto();

        when(footballerRepository.findById(1L)).thenReturn(Optional.of(footballer));
        when(commandRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CommandNotFoundException.class, () -> footballerService.editInfoAboutFootballer(1L, dto));

        verify(footballerRepository).findById(1L);
        verify(commandRepository).findById(1L);
    }

    @Test
    void deleteFootballerByIdTest() {
        Footballer footballer = ModelUtils.getFootballer();

        when(footballerRepository.findById(1L)).thenReturn(Optional.of(footballer));

        footballerService.deleteFootballerById(1L);

        verify(footballerRepository).findById(1L);
        verify(footballerRepository).delete(footballer);
    }
}
