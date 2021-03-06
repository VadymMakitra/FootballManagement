package management.conroller;

import lombok.AllArgsConstructor;
import management.dto.AllInfoAboutFootballerDto;
import management.dto.EditInfoAboutFootballerDto;
import management.dto.FootballerDto;
import management.service.FootballerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/footballer")
public class FootballerController {
    private final FootballerService footballerService;

    /**
     * Controller for create new footballer.
     *
     * @param dto - info about new footballer
     */
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addNewFootballer(
            @Valid @RequestBody FootballerDto dto) {
        footballerService.addFootballer(dto);
        return ResponseEntity.status(201).build();
    }

    /**
     * Controller for getting all info about footballers.
     *
     * @return {@link AllInfoAboutFootballerDto}
     */
    @GetMapping("/allInfo")
    public ResponseEntity<List<AllInfoAboutFootballerDto>> getInfo() {
        return ResponseEntity.status(200).body(footballerService.getInfoAboutFootballers());
    }

    /**
     * Controller for updating info about footballer.
     *
     * @param id  of current footballer
     * @param dto - info that need to update
     */
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> editInfo(
            @PathVariable Long id, @Valid @RequestBody EditInfoAboutFootballerDto dto) {
        footballerService.editInfoAboutFootballer(id, dto);
        return ResponseEntity.status(200).build();
    }

    /**
     * Controller for deleting footballer.
     *
     * @param id of footballer that will need to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFootballer(
            @PathVariable Long id) {
        footballerService.deleteFootballerById(id);
        return ResponseEntity.status(200).build();
    }

}
