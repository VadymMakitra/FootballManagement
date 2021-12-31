package management;

import management.dto.CommandDto;
import management.dto.EditInfoAboutFootballerDto;
import management.dto.FootballerDto;
import management.dto.TransferDto;

import java.util.List;

public class ModelUtils {
    public static CommandDto getCommandDto() {
        return CommandDto.builder()
                .name("Manchester")
                .budget(1000000L)
                .commission(4)
                .footballerDtoList(getFootballerDtoList())
                .build();
    }

    public static List<FootballerDto> getFootballerDtoList() {
        return List.of(FootballerDto.builder()
                .firstName("Marvin")
                .lastName("Brain")
                .age(23)
                .commandId(1L)
                .experience(1)
                .build());
    }

    public static FootballerDto getFootballerDto() {
        return FootballerDto.builder()
                .commandId(1L)
                .experience(3)
                .age(22)
                .firstName("John")
                .lastName("Smith")
                .build();
    }

    public static EditInfoAboutFootballerDto getEditedInfoAboutFootballerDto() {
        return EditInfoAboutFootballerDto.builder()
                .age(22)
                .commandId(1L)
                .experience(3)
                .firstName("Marvin")
                .lastName("Brain")
                .build();
    }

    public static TransferDto getTransferDto() {
        return TransferDto.builder()
                .footballerId(1L)
                .commandId(2L)
                .build();
    }
}
