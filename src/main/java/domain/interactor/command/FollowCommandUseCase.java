package domain.interactor.command;

import data.model.command.BaseCommand;
import data.model.command.FollowCommand;
import data.model.user.Message;
import domain.CommandRepository;
import domain.UserRepository;

import java.util.List;

public class FollowCommandUseCase extends BaseCommandUseCase {

    private String textCommand;
    private UserRepository userRepository;

    public FollowCommandUseCase(String textCommand, UserRepository userRepository, CommandRepository commandRepository) {
        super(commandRepository);
        this.textCommand = textCommand;
        this.userRepository = userRepository;
    }

    @Override
    public List<Message> execute() {
        FollowCommand command = commandRepository.createFollowCommand(textCommand, userRepository);
        return command.execute();
    }

    @Override
    public Type getType() {
        return Type.FOLLOW;
    }
}
