package management.mapper;

import management.ModelUtils;
import management.dto.FootballerDto;
import management.entity.Footballer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CreateFootballerDtoMapperTest {
    @InjectMocks
    CreateFootballerDtoMapper mapper;

    @Test
    void convert(){
        FootballerDto footballerDto = ModelUtils.getFootballerDto();
        Footballer footballer = ModelUtils.getFootballerForMapper();

        assertEquals(footballer,mapper.convert(footballerDto));
    }
}
