package management.mapper;

import management.ModelUtils;
import management.dto.AllInfoAboutCommandDto;
import management.entity.Command;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AllInfoAboutCommandDtoMapperTest {
    @InjectMocks
    AllInfoAboutCommandDtoMapper mapper;

    @Test
    void convert() {
        Command command = ModelUtils.getCommandForMapper();
        AllInfoAboutCommandDto dto = ModelUtils.getAllInfoAboutCommandDtoForMapper();

        assertEquals(dto.getCommandId(), mapper.convert(command).getCommandId());
        assertEquals(dto.getCommandBudget(), mapper.convert(command).getCommandBudget());
        assertEquals(dto.getCommandCommission(), mapper.convert(command).getCommandCommission());
        assertEquals(dto.getCommandName(), mapper.convert(command).getCommandName());
        assertEquals(dto.getInfoAboutFootballerDtos(), mapper.convert(command).getInfoAboutFootballerDtos());
    }
}
