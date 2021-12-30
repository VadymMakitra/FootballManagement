package management.conroller;

import lombok.AllArgsConstructor;
import management.service.ManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/footballer/management")
public class ManagementController {
    private final ManagementService managementService;

    /**
     * Controller for transfer footballer to another team.
     *
     * @param footballerId ID of the footballer that needs transfer
     * @param commandId    id of command for transfer footballer
     */
    @PostMapping("/transfer")
    public ResponseEntity<HttpStatus> transferPlayer(
            Long footballerId, Long commandId) {
        managementService.transferFootballer(footballerId, commandId);
        return ResponseEntity.status(200).build();
    }
}
