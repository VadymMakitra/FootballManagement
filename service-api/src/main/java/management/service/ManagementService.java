package management.service;

import management.dto.TransferDto;

public interface ManagementService {
    /**
     * Method for transfer footballer to another team.
     *
     * @param transferDto dto with parameters for transfer
     */
    void transferFootballer(TransferDto transferDto);
}
