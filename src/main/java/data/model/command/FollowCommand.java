package data.model.command;

import data.model.user.Message;
import domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class FollowCommand extends BaseCommand {

    private final static String FOLLOWS_COMMAND_EXECUTE_SYMBOL = " follows ";

    private String textCommand;
    private UserRepository userRepository;

    public FollowCommand(String textCommand, UserRepository userRepository) {
        this.textCommand = textCommand;
        this.userRepository = userRepository;
    }

    @Override
    public List<Message> execute() {
        String[] parts = textCommand.split(FOLLOWS_COMMAND_EXECUTE_SYMBOL);
        userRepository.createFollow(parts[0], parts[1]);
        return new ArrayList<>();
    }

}
