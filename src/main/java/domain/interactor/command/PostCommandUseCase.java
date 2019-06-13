package domain.interactor.command;

import data.model.command.BaseCommand;
import data.model.command.PostCommand;
import data.model.user.Message;
import domain.CommandRepository;
import domain.UserRepository;

import java.util.List;

public class PostCommandUseCase extends BaseCommandUseCase{

    private String textCommand;
    private UserRepository userRepository;

    public PostCommandUseCase(String textCommand, UserRepository userRepository, CommandRepository commandRepository) {
        super(commandRepository);
        this.textCommand = textCommand;
        this.userRepository = userRepository;
    }

    @Override
    public List<Message> execute() {
        PostCommand command = commandRepository.createPostCommand(textCommand, userRepository);
        return command.execute();
    }

    @Override
    public Type getType() {
        return Type.POST;
    }
}
