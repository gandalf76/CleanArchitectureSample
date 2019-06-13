package domain.interactor.user;

import data.model.user.Message;
import domain.UserRepository;

import java.util.List;

public abstract class BaseUserUseCase {

    protected UserRepository userRepository;

    public BaseUserUseCase(UserRepository userRepository) {
        this.userRepository= userRepository;
    }

    public abstract List<Message> execute();

}
