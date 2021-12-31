package management.constant;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ErrorMessage {
    public static final String FOOTBALLER_NOT_EXIST = "Footballer with chosen id does not exist";
    public static final String COMMAND_NOT_EXIST = "Command with chosen id does not exist";
    public static final String FOOTBALLER_ALREADY_IN_COMMAND = "The chosen footballer already in command that you chose";
    public static final String COMMAND_DONT_HAVE_ENOUGH_BUDGET = "The chose command don't have enough budget for this transfer";
}
