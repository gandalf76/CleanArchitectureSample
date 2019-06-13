package data;

import data.model.command.FollowCommand;
import data.model.command.PostCommand;
import data.model.command.ReadCommand;
import data.model.command.WallCommand;
import domain.CommandRepository;
import domain.UserRepository;

public class CommandRepositoryImpl implements CommandRepository {

    @Override
    public PostCommand createPostCommand(String textCommand, UserRepository userRepository) {
        return new PostCommand(textCommand, userRepository);
    }

    @Override
    public ReadCommand createReadCommand(String userName, UserRepository userRepository) {
        return new ReadCommand(userName, userRepository);
    }

    @Override
    public FollowCommand createFollowCommand(String textCommand, UserRepository userRepository) {
        return new FollowCommand(textCommand, userRepository);
    }

    @Override
    public WallCommand createWallCommand(String textCommand, UserRepository userRepository) {
        return new WallCommand(textCommand, userRepository);
    }
}
