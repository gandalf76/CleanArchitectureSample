package data.model.command;

import data.model.user.Message;

import java.util.List;

public abstract class BaseCommand {

    public static final String COMMAND_EXECUTE_SYMBOL = "->";

    public abstract List<Message> execute();


}
