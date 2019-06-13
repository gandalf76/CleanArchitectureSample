package domain.interactor.user;

import data.model.user.Message;
import domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class CreateFollowUseCase extends BaseUserUseCase {

    private String userName;
    private String followedUserName;

    public CreateFollowUseCase(String userName, String followedUserName, UserRepository userRepository) {
        super(userRepository);
        this.userName = userName;
        this.followedUserName = followedUserName;
    }

    @Override
    public List<Message> execute() {
        super.userRepository.createFollow(userName, followedUserName);
        return new ArrayList<>();
    }
}
