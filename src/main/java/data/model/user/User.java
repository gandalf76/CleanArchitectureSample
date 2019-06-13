package data.model.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private String name;
    private List<User> followedUsers;

    /**
     * @param name The name of the user
     */
    public User(String name) {
        this.name = name;
        this.followedUsers = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<User> getFollowedUsers() {
        return Collections.unmodifiableList(this.followedUsers);
    }

    public void addFollowedUser(User user) {
        followedUsers.add(user);
    }
}
