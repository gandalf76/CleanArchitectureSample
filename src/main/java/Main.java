import data.CommandRepositoryImpl;
import data.UserRepositoryImpl;
import domain.CommandRepository;
import domain.UserRepository;
import domain.interactor.command.BaseCommandUseCase;
import domain.interactor.user.UseCaseFactory;
import presentation.Console;
import presentation.mapper.Mapper;
import presentation.model.ConsoleMessage;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Console console = new Console();
        UserRepository userRepository = new UserRepositoryImpl();
        CommandRepository commandRepository = new CommandRepositoryImpl();
        UseCaseFactory useCaseFactory = new UseCaseFactory();

       while(true) {
            String input = console.readInput();
            BaseCommandUseCase useCase = useCaseFactory.createUseCase(input, userRepository, commandRepository);
            Mapper mapper = new Mapper();
            List<ConsoleMessage>  messages = useCase.execute().stream().map(message ->
                    mapper.asUIModel(message, useCase.getType())
            ).collect(Collectors.toList());

            for(ConsoleMessage message : messages) {
                console.writeMessage(message.getText());
            }
        }
    }
}
