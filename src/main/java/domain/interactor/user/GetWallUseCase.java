package domain.interactor.user;

import data.model.user.Message;
import domain.UserRepository;

import java.util.List;

public class GetWallUseCase extends BaseUserUseCase {

    private String userName;

    public GetWallUseCase(String userName, UserRepository userRepository) {
        super(userRepository);
        this.userName = userName;
    }

    @Override
    public List<Message> execute() {
        return super.userRepository.getUserAndFollowedUserMessagesListByUser(userName);
    }
}
