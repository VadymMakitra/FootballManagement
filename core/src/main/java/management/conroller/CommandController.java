package management.conroller;

import lombok.AllArgsConstructor;
import management.dto.AllInfoAboutCommandDto;
import management.dto.CommandDto;
import management.service.CommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/command")
public class CommandController {
    private final CommandService commandService;

    /**
     * Controller for create new command.
     *
     * @param dto - info about new command
     */
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addNewCommand(
            @RequestBody CommandDto dto) {
        commandService.addCommand(dto);
        return ResponseEntity.status(201).build();
    }

    /**
     * Controller for getting info about commands.
     *
     * @return {@link AllInfoAboutCommandDto} info about all commands
     */
    @GetMapping("/infoAboutCommands")
    public ResponseEntity<List<AllInfoAboutCommandDto>> getInfoAboutCommands() {
        return ResponseEntity.status(200).body(commandService.getInfoAboutAllCommand());
    }

    /**
     * Controller for editing commands.
     *
     * @param id  of command that need to edit
     * @param dto edited parameters
     */
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> editCommands(
            @PathVariable Long id, @RequestBody CommandDto dto) {
        commandService.editInfoAboutCommand(id, dto);
        return ResponseEntity.status(200).build();
    }

    /**
     * Controller for deleting command.
     *
     * @param id of command that need to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCommand(
            @PathVariable Long id) {
        commandService.deleteCommandById(id);
        return ResponseEntity.status(200).build();
    }
}
