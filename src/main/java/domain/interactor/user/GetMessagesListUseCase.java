package domain.interactor.user;

import data.model.user.Message;
import domain.UserRepository;

import java.util.List;

public class GetMessagesListUseCase extends BaseUserUseCase {

    private String userName;

    public GetMessagesListUseCase(String userName, UserRepository userRepository) {
        super(userRepository);
        this.userName = userName;
    }

    @Override
    public List<Message> execute() {
        return super.userRepository.getMessagesListByUser(userName);
    }
}
