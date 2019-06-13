package domain.interactor.user;

import data.model.command.FollowCommand;
import domain.CommandRepository;
import domain.UserRepository;
import domain.interactor.command.*;

public class UseCaseFactory {

    private final static String POST_COMMAND_PATTERN   = ".*\\s->\\s.*";
    private final static String READ_COMMAND_PATTERN   = ".*";
    private final static String FOLLOW_COMMAND_PATTERN = ".*\\sfollows\\s.*";
    private final static String WALL_COMMAND_PATTERN   = ".*\\swall";
    private final static String EXIT_COMMAND_PATTERN   = "exit";

    public BaseCommandUseCase createUseCase(String commandText, UserRepository userRepository, CommandRepository commandRepository) {
        if (commandText.matches(POST_COMMAND_PATTERN)) {
            //case EXIT_COMMAND_PATTERN   => new ExitCommand(new System)
            return new PostCommandUseCase(commandText, userRepository, commandRepository);
        } else if(commandText.matches(FOLLOW_COMMAND_PATTERN)) {
            return new FollowCommandUseCase(commandText, userRepository, commandRepository);
        } else if(commandText.matches(WALL_COMMAND_PATTERN)) {
            return new WallCommandUseCase(commandText, userRepository, commandRepository);
        } else if(commandText.matches(READ_COMMAND_PATTERN)) {
            return new ReadCommandUseCase(commandText, userRepository, commandRepository);
        }
        return null;
    }

}
