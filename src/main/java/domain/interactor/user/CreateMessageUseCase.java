package domain.interactor.user;

import data.model.user.Message;
import domain.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateMessageUseCase extends BaseUserUseCase {

    private String userName;
    private String messageText;
    private Date messageDate;

    public CreateMessageUseCase(String userName, String messageText, Date messageDate, UserRepository userRepository) {
        super(userRepository);
        this.userName = userName;
        this.messageText = messageText;
        this.messageDate = messageDate;
    }

    @Override
    public List<Message> execute() {
        super.userRepository.createNewMessage(userName, messageText, messageDate);
        return new ArrayList<>();
    }
}
