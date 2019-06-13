package data.model.command;

import data.model.user.Message;
import domain.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostCommand extends BaseCommand {

    private String textCommand;
    private UserRepository userRepository;

    /**
     * @param textCommand The command text inserted by the user
     * @param userRepository User repository
     */
    public PostCommand(String textCommand, UserRepository userRepository) {
        this.textCommand = textCommand;
        this.userRepository = userRepository;
    }

    @Override
    public List<Message> execute() {
        String[] parts = textCommand.split(COMMAND_EXECUTE_SYMBOL);
        userRepository.createNewMessage(parts[0].trim(), parts[1].trim(), new Date());
        return new ArrayList<>();
    }


}
