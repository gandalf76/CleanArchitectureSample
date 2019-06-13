package utils;

import data.model.user.Message;

import java.util.*;

public class MockUtilsUserTest {

    public final static String MOCK_USER_NAME = "mock_user_name";
    public final static String MOCK_FOLLOWED_USER_NAME = "mock_user_followed_name";
    public final static String MOCK_MESSAGE_TEXT = "mock_message_text";
    public final static String MOCK_MESSAGE_TEXT_2 = "mock_message_text_2";
    public final static String MOCK_MESSAGE_TEXT_3 = "mock_message_text_3";

    public static Date buildMockDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019,1,1);
        return calendar.getTime();
    }

    public static Message buildMessage(Date date) {
        return new Message.Builder()
                .setUserName(MOCK_USER_NAME)
                .setText(MOCK_MESSAGE_TEXT)
                .setDate(date)
                .build();
    }

    public static List<Message> buildMockMessageList(Date date) {
        Message message = new Message.Builder()
                .setUserName(MOCK_USER_NAME)
                .setText(MOCK_MESSAGE_TEXT)
                .setDate(date)
                .build();
       List<Message> messages = new ArrayList<>();
       messages.add(message);
       return messages;
    }

    public static List<Message> buildMockMessageFollowedList(Date date, Date followedDate) {
        Message message = new Message.Builder()
                .setUserName(MOCK_USER_NAME)
                .setText(MOCK_MESSAGE_TEXT)
                .setDate(date)
                .build();
        Message followedMessage = new Message.Builder()
                .setUserName(MOCK_FOLLOWED_USER_NAME)
                .setText(MOCK_MESSAGE_TEXT_2)
                .setDate(followedDate)
                .build();
        List<Message> messages = new ArrayList<>();
        messages.add(message);
        messages.add(followedMessage);
        return messages;
    }
}
