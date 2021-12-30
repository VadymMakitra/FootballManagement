package mapper;

import management.dto.AllInfoAboutFootballerDto;
import management.entity.Footballer;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class AllInfoAboutFootballerMapper extends AbstractConverter<Footballer, AllInfoAboutFootballerDto> {

    @Override
    protected AllInfoAboutFootballerDto convert(Footballer footballer) {
        return AllInfoAboutFootballerDto.builder()
                .id(footballer.getId())
                .age(footballer.getAge())
                .experience(footballer.getExperience())
                .fullName(footballer.getFullName())
                .build();
    }
}
