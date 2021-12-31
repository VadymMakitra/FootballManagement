package management.mapper;

import management.dto.FootballerDto;
import management.entity.Footballer;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class CreateFootballerDtoMapper extends AbstractConverter<FootballerDto, Footballer> {

    @Override
    protected Footballer convert(FootballerDto dto) {
        return Footballer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .experience(dto.getExperience())
                .build();
    }
}
