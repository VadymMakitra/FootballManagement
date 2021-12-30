package management.service;

import management.dto.AllInfoAboutFootballerDto;
import management.dto.EditInfoAboutFootballerDto;
import management.dto.FootballerDto;

import java.util.List;

public interface FootballerService {
    /**
     * Method for add new footballer.
     *
     * @param dto {@link FootballerDto}
     */
    void addFootballer(FootballerDto dto);

    /**
     * Method for getting all info about footballers.
     *
     * @return {@link AllInfoAboutFootballerDto}
     */
    List<AllInfoAboutFootballerDto> getInfoAboutFootballers();

    /**
     * Method for edit info about footballer by id.
     *
     * @param id  - id of footballer
     * @param dto - new info about footballer
     */
    void editInfoAboutFootballer(Long id, EditInfoAboutFootballerDto dto);

    /**
     * Method for deleting footballer by id.
     *
     * @param id - id of footballer
     */
    void deleteFootballerById(Long id);
}
