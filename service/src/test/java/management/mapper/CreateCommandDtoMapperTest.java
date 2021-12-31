package management.mapper;

import management.ModelUtils;
import management.dto.CommandDto;
import management.entity.Command;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CreateCommandDtoMapperTest {
    @InjectMocks
    CreateCommandDtoMapper mapper;

    @Test
    void convert() {
        Command command = ModelUtils.getCommand();
        CommandDto commandDto = ModelUtils.getCommandDto();

        assertEquals(command, mapper.convert(commandDto));
    }
}
