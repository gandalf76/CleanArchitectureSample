package domain;

import data.model.command.FollowCommand;
import data.model.command.PostCommand;
import data.model.command.ReadCommand;
import data.model.command.WallCommand;

public interface CommandRepository {

    PostCommand createPostCommand(String textCommand, UserRepository userRepository);

    ReadCommand createReadCommand(String userName, UserRepository userRepository);

    FollowCommand createFollowCommand(String textCommand, UserRepository userRepository);

    WallCommand createWallCommand(String textCommand, UserRepository userRepository);
}
