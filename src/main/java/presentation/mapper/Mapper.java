package presentation.mapper;

import data.model.user.Message;
import domain.interactor.command.BaseCommandUseCase;
import presentation.model.ConsoleMessage;

public class Mapper {

    public ConsoleMessage asUIModel(Message message, BaseCommandUseCase.Type commandType) {

        switch(commandType) {
            case WALL:
                return new ConsoleMessage(String.format("%s - %s (%s)", message.getUserName(), message.getText(), message.getDate()));
            case READ:
                return new ConsoleMessage(String.format("%s (%s)", message.getText(), message.getDate()));
        }
        return new ConsoleMessage(message.getText());
    }
}
