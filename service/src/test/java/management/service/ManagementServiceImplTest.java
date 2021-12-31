package management.service;

import management.ModelUtils;
import management.dto.TransferDto;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManagementServiceImplTest {
    @Mock
    private CommandRepository commandRepository;
    @Mock
    private FootballerRepository footballerRepository;
    @InjectMocks
    ManagementServiceImp managementService;

    @Test
    void transferFootballerTest() {
        Footballer footballer = ModelUtils.getFootballer();
        Command newCommand = ModelUtils.getNewCommand();
        TransferDto transferDto = ModelUtils.getTransferDto();

        when(footballerRepository.findById(1L)).thenReturn(Optional.of(footballer));
        when(commandRepository.findById(2L)).thenReturn(Optional.of(newCommand));
        when(footballerRepository.save(footballer)).thenReturn(footballer);
        when(commandRepository.save(newCommand)).thenReturn(newCommand);

        managementService.transferFootballer(transferDto);

        verify(footballerRepository).findById(1L);
        verify(commandRepository).findById(2L);
        verify(footballerRepository).save(footballer);
        verify(commandRepository).save(newCommand);
    }

    @Test
    void transferFootballerThrowsFootballerNotFoundExceptionTest() {
        when(footballerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FootballerNotFoundException.class, () -> managementService.transferFootballer(ModelUtils.getTransferDto()));

        verify(footballerRepository).findById(1L);
    }

    @Test
    void transferFootballerThrowsCommandNotFoundExceptionTest() {
        Footballer footballer = ModelUtils.getFootballer();

        when(footballerRepository.findById(1L)).thenReturn(Optional.of(footballer));
        when(commandRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CommandNotFoundException.class, () -> managementService.transferFootballer(ModelUtils.getTransferDto()));

        verify(footballerRepository).findById(1L);
        verify(commandRepository).findById(2L);
    }

    @Test
    void transferFootballerThrowsFootballerAlreadyInCommandExceptionTest() {
        Footballer footballer = ModelUtils.getFootballer();
        Command newCommand = ModelUtils.getNewCommand();

        when(footballerRepository.findById(1L)).thenReturn(Optional.of(footballer));
        when(commandRepository.findById(2L)).thenReturn(Optional.of(newCommand));
        when(footballerRepository.save(footballer)).thenReturn(footballer);
        when(commandRepository.save(newCommand)).thenReturn(newCommand);

        managementService.transferFootballer(ModelUtils.getTransferDto());

        verify(footballerRepository).findById(1L);
        verify(commandRepository).findById(2L);
        verify(footballerRepository).save(footballer);
        verify(commandRepository).save(newCommand);
    }

}
