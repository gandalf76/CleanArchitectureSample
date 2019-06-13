package data.model.command;

import data.model.user.Message;
import domain.UserRepository;

import java.util.List;

public class WallCommand extends BaseCommand{

    private final static String WALL_COMMAND_EXECUTE_SYMBOL = " wall";

    private String textCommand;
    private UserRepository userRepository;

    /**
     * @param textCommand The text of the command
     * @param userRepository User repository
     */
    public WallCommand(String textCommand, UserRepository userRepository) {
        this.textCommand = textCommand;
        this.userRepository = userRepository;
    }

    @Override
    public List<Message> execute() {
        String[] parts = textCommand.split(WALL_COMMAND_EXECUTE_SYMBOL);
        return userRepository.getUserAndFollowedUserMessagesListByUser(parts[0]);
    }

}
