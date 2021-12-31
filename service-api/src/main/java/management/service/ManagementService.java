package management.service;

public interface ManagementService {
    /**
     * Method for transfer footballer to another team.
     *
     * @param footballerId ID of the footballer that needs transfer
     * @param commandId    id of command for transfer footballer
     */
    void transferFootballer(Long footballerId, Long commandId);
}
