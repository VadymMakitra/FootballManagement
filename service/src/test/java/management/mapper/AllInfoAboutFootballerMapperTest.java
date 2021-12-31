package management.mapper;

import management.ModelUtils;
import management.dto.AllInfoAboutFootballerDto;
import management.entity.Footballer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AllInfoAboutFootballerMapperTest {
    @InjectMocks
    AllInfoAboutFootballerMapper mapper;

    @Test
    void convert(){
        Footballer footballer = ModelUtils.getFootballer();
        AllInfoAboutFootballerDto infoAboutFootballerDto = ModelUtils.getAllInfoAboutFootballerDto().get(0);

        assertEquals(infoAboutFootballerDto,mapper.convert(footballer));
    }
}
