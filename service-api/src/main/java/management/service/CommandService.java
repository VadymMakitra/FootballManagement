package management.service;

import management.dto.AllInfoAboutCommandDto;
import management.dto.CommandDto;

import java.util.List;

public interface CommandService {
    /**
     * Method for create new command.
     *
     * @param commandDto - info about created command
     */
    void addCommand(CommandDto commandDto);

    /**
     * Method for getting all info about commands.
     *
     * @return {@link AllInfoAboutCommandDto} info about commands
     */
    List<AllInfoAboutCommandDto> getInfoAboutAllCommand();

    /**
     * Method for edit needed info about command.
     *
     * @param id  id of command that need to edit
     * @param dto new parameters for command
     */
    void editInfoAboutCommand(Long id, CommandDto dto);

    /**
     * Method for deleting command by id.
     *
     * @param id of command that need to delete
     */
    void deleteCommandById(Long id);
}
