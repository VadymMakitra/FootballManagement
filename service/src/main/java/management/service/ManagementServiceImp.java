package management.service;

import lombok.AllArgsConstructor;
import management.constant.ErrorMessage;
import management.dto.TransferDto;
import management.entity.Command;
import management.entity.Footballer;
import management.exception.CommandNotFoundException;
import management.exception.FootballerAlreadyInCommandException;
import management.exception.FootballerNotFoundException;
import management.exception.NotEnoughBudgetException;
import management.repository.CommandRepository;
import management.repository.FootballerRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManagementServiceImp implements ManagementService {
    private final CommandRepository commandRepository;
    private final FootballerRepository footballerRepository;

    @Override
    public void transferFootballer(TransferDto dto) {
        Footballer footballer = footballerRepository.findById(dto.getFootballerId()).orElseThrow(
                () -> new FootballerNotFoundException(ErrorMessage.FOOTBALLER_NOT_EXIST));
        Command command = commandRepository.findById(dto.getCommandId()).orElseThrow(
                () -> new CommandNotFoundException(ErrorMessage.COMMAND_NOT_EXIST));

        checkIfFootballerAlreadyInCommand(command, footballer);
        Command newCommand = buildNewCommand(command, footballer);
        Command oldCommand = buildOldCommand(footballer.getCommand(), footballer);

        Footballer transferredFootballer = buildFootballer(footballer, newCommand);

        footballerRepository.save(transferredFootballer);
        commandRepository.save(oldCommand);
        commandRepository.save(newCommand);
    }

    private Integer getFullPrice(Command command, Footballer footballer) {
        int transferPrise = (footballer.getExperience() * 100000) / footballer.getAge();
        int commissionFromTransfer = transferPrise * (command.getCommission() / 100);
        return transferPrise + commissionFromTransfer;
    }

    private Footballer buildFootballer(Footballer footballer, Command command) {
        return Footballer.builder()
                .id(footballer.getId())
                .experience(footballer.getExperience())
                .age(footballer.getAge())
                .firstName(footballer.getFirstName())
                .lastName(footballer.getLastName())
                .command(command)
                .build();
    }

    private Command buildOldCommand(Command command, Footballer footballer) {
        return Command.builder()
                .id(command.getId())
                .commission(command.getCommission())
                .budget(command.getBudget() + getFullPrice(command, footballer))
                .name(command.getName())
                .build();
    }

    private Command buildNewCommand(Command command, Footballer footballer) {
        checkIfCommandHaveEnoughBudgetForTransfer(command, footballer);
        return Command.builder()
                .id(command.getId())
                .commission(command.getCommission())
                .budget(command.getBudget() - getFullPrice(command, footballer))
                .name(command.getName())
                .build();
    }

    private void checkIfFootballerAlreadyInCommand(Command command, Footballer footballer) {
        if (footballer.getCommand().equals(command)) {
            throw new FootballerAlreadyInCommandException(ErrorMessage.FOOTBALLER_ALREADY_IN_COMMAND);
        }
    }

    private void checkIfCommandHaveEnoughBudgetForTransfer(Command command, Footballer footballer) {
        if (command.getBudget() < command.getBudget() - getFullPrice(command, footballer)) {
            throw new NotEnoughBudgetException(ErrorMessage.COMMAND_DONT_HAVE_ENOUGH_BUDGET);
        }
    }
}
