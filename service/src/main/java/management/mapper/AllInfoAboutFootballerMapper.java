package management.mapper;

import management.dto.AllInfoAboutCommandDto;
import management.dto.AllInfoAboutFootballerDto;
import management.dto.InfoAboutFootballerCommandDto;
import management.entity.Footballer;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class AllInfoAboutFootballerMapper extends AbstractConverter<Footballer, AllInfoAboutFootballerDto> {

    @Override
    protected AllInfoAboutFootballerDto convert(Footballer footballer) {
        InfoAboutFootballerCommandDto info = InfoAboutFootballerCommandDto.builder()
                .commandBudget(footballer.getCommand().getBudget())
                .commandCommission(footballer.getCommand().getCommission())
                .commandId(footballer.getCommand().getId())
                .commandName(footballer.getCommand().getName())
                .build();
        return AllInfoAboutFootballerDto.builder()
                .id(footballer.getId())
                .age(footballer.getAge())
                .experience(footballer.getExperience())
                .fullName(footballer.getFullName())
                .infoAboutCommandDto(info)
                .build();
    }
}
