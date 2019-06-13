package domain.interactor.command;

import data.model.command.BaseCommand;
import data.model.command.ReadCommand;
import data.model.user.Message;
import domain.CommandRepository;
import domain.UserRepository;

import java.util.List;

public class ReadCommandUseCase extends BaseCommandUseCase {

    private String userName;
    private UserRepository userRepository;

    public ReadCommandUseCase(String userName, UserRepository userRepository, CommandRepository commandRepository) {
        super(commandRepository);
        this.userName = userName;
        this.userRepository = userRepository;
    }

    @Override
    public List<Message> execute() {
        ReadCommand command = commandRepository.createReadCommand(userName, userRepository);
        return command.execute();
    }

    @Override
    public Type getType() {
        return Type.READ;
    }
}
