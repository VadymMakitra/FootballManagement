package management;

import management.dto.*;
import management.entity.Command;
import management.entity.Footballer;

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

    public static Command getCommand() {
        return Command.builder()
                .name("Manchester")
                .budget(1000000L)
                .commission(4)
                .footballers(getFootballerList())
                .build();
    }


    public static Command getNewCommand() {
        return Command.builder()
                .name("Manchester")
                .budget(1013636L)
                .commission(4)
                .footballers(getNewFootballerList())
                .build();
    }

    public static List<Footballer> getFootballerList() {
        return List.of(Footballer.builder()
                .id(1L)
                .age(23)
                .firstName("Marvin")
                .lastName("Brain")
                .experience(1)
                .build());
    }

    public static List<Footballer> getNewFootballerList() {
        return List.of(Footballer.builder()
                .id(1L)
                .age(23)
                .firstName("Hilla")
                .lastName("Min")
                .experience(1)
                .build());
    }

    public static List<Command> getCommandList(){
        return List.of(Command.builder()
                .commission(4)
                .name("Manchester")
                .budget(1000000L)
                .footballers(getFootballerList())
                .build());
    }

    public static AllInfoAboutCommandDto getAllInfoAboutCommandDto(){
        return AllInfoAboutCommandDto.builder()
                .commandBudget(1000000L)
                .commandCommission(4)
                .commandName("Manchester")
                .infoAboutFootballerDtos(getAllInfoAboutFootballerDto())
                .build();
    }

    public static List<AllInfoAboutFootballerDto> getAllInfoAboutFootballerDto(){
        return List.of(AllInfoAboutFootballerDto.builder()
                .fullName("John Smith")
                .age(23)
                .experience(1)
                .id(1L)
                .build());
    }

    public static FootballerDto getFootballerDto(){
        return FootballerDto.builder()
                .commandId(1L)
                .experience(3)
                .age(22)
                .firstName("John")
                .lastName("Smith")
                .build();
    }

    public static Footballer getFootballer(){
        return Footballer.builder()
                .experience(3)
                .lastName("Smith")
                .firstName("John")
                .age(22)
                .command(getCommand())
                .build();
    }

    public static EditInfoAboutFootballerDto getEditedInfoAboutFootballerDto(){
        return EditInfoAboutFootballerDto.builder()
                .age(22)
                .commandId(1L)
                .experience(3)
                .firstName("John")
                .lastName("Smith")
                .build();
    }
}
