package domain.interactor.command;

import data.model.user.Message;
import domain.CommandRepository;
import java.util.List;


public abstract class BaseCommandUseCase {

    protected CommandRepository commandRepository;

    public BaseCommandUseCase(CommandRepository commandRepository) {
        this.commandRepository= commandRepository;
    }

    public abstract List<Message> execute();

    public enum Type {
        READ,
        FOLLOW,
        POST,
        WALL
    }

    public abstract Type getType();
}
