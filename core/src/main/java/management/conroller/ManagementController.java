package management.conroller;

import lombok.AllArgsConstructor;
import management.dto.TransferDto;
import management.service.ManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * @param dto with needed parameters for transfer
     */
    @PostMapping("/transfer")
    public ResponseEntity<HttpStatus> transferPlayer(
            @RequestBody TransferDto dto) {
        managementService.transferFootballer(dto);
        return ResponseEntity.status(200).build();
    }
}
