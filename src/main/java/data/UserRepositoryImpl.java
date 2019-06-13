package data;

import data.model.user.Message;
import data.model.user.User;
import domain.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

    private Map<String, User> users;
    private List<Message> messages;

    public UserRepositoryImpl() {
        this.users = new HashMap<>();
        this.messages = new ArrayList<>();
    }

    public void createNewMessage(String userName, String text, Date date) {
        createAndAddUserToUsers(userName);
        messages.add(new Message.Builder()
                        .setUserName(userName)
                        .setText(text)
                        .setDate(date)
                        .build());
    }

    public List<Message> getMessagesListByUser(String userName) {
        User user = users.get(userName);
        if (user != null) {
            return this.messages.stream().filter(message -> message.getUserName().equals(userName)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void createFollow(String userName, String followedUserName) {
        User user = users.get(userName);
        if (user == null) {
            user = createAndAddUserToUsers(userName);
        }
        User followedUser = users.get(followedUserName);
        if (followedUser == null) {
            followedUser = createAndAddUserToUsers(followedUserName);
        }
        user.addFollowedUser(followedUser);
    }

    private User createAndAddUserToUsers(String userName) {
        User user = new User(userName);
        users.put(userName, user);
        return user;
    }

    public List<Message> getUserAndFollowedUserMessagesListByUser(String userName) {
        User user = users.get(userName);
        if (user != null) {
            return this.messages.stream().filter(message -> isMessageByUser(message, userName) || isMessageByFollwedUser(message, user))
                            .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private boolean isMessageByUser(Message message, String userName) {
        return message.getUserName().equals(userName);
    }

    private boolean isMessageByFollwedUser(Message message, User user) {
        return !user.getFollowedUsers().stream().filter(followed -> message.getUserName().equals(followed.getName())).collect(Collectors.toList()).isEmpty();
    }
}
