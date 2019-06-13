package data.model.command;

import data.model.user.Message;
import domain.UserRepository;

import java.util.List;

public class ReadCommand extends BaseCommand{

    private String userName;
    private UserRepository userRepository;

    /**
     * @param userName The user name
     * @param userRepository User repository
     */
    public ReadCommand(String userName, UserRepository userRepository) {
        this.userName = userName;
        this.userRepository = userRepository;
    }

    @Override
    public List<Message> execute() {
        return userRepository.getMessagesListByUser(this.userName);
    }

}
