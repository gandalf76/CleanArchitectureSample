package domain;

import data.model.user.Message;
import java.util.Date;
import java.util.List;

public interface UserRepository {

    void createNewMessage(String userName, String text, Date date);

    List<Message> getMessagesListByUser(String userName);

    void createFollow(String userName, String followedUserName);

    List<Message> getUserAndFollowedUserMessagesListByUser(String userName);

}
