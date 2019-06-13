import data.UserRepositoryImpl;
import data.model.user.Message;
import domain.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.MockUtilsUserTest;

import java.util.Date;
import java.util.List;


public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void before() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    public void createNewMessageTest() {
        Date mockDate = MockUtilsUserTest.buildMockDate();

        userRepository.createNewMessage(MockUtilsUserTest.MOCK_USER_NAME, MockUtilsUserTest.MOCK_MESSAGE_TEXT, mockDate);
        List<Message> messages = userRepository.getMessagesListByUser(MockUtilsUserTest.MOCK_USER_NAME);

        Assert.assertNotNull(messages);
        Assert.assertEquals(1, messages.size());
        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, messages.get(0).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT, messages.get(0).getText());
        Assert.assertEquals(mockDate, messages.get(0).getDate());
    }

    @Test
    public void getMessageListByUser() {
        Date mockDate = MockUtilsUserTest.buildMockDate();
        Date mockDate2 = MockUtilsUserTest.buildMockDate();
        Date mockDate3 = MockUtilsUserTest.buildMockDate();

        userRepository.createNewMessage(MockUtilsUserTest.MOCK_USER_NAME, MockUtilsUserTest.MOCK_MESSAGE_TEXT, mockDate);
        userRepository.createNewMessage(MockUtilsUserTest.MOCK_USER_NAME, MockUtilsUserTest.MOCK_MESSAGE_TEXT_2, mockDate2);
        userRepository.createNewMessage(MockUtilsUserTest.MOCK_USER_NAME, MockUtilsUserTest.MOCK_MESSAGE_TEXT_3, mockDate3);
        List<Message> messages = userRepository.getMessagesListByUser(MockUtilsUserTest.MOCK_USER_NAME);

        Assert.assertNotNull(messages);
        Assert.assertEquals(3, messages.size());
        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, messages.get(0).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT, messages.get(0).getText());
        Assert.assertEquals(mockDate, messages.get(0).getDate());
        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, messages.get(1).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT_2, messages.get(1).getText());
        Assert.assertEquals(mockDate2, messages.get(1).getDate());
        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, messages.get(2).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT_3, messages.get(2).getText());
        Assert.assertEquals(mockDate3, messages.get(2).getDate());
    }

    @Test
    public void createFollowTest() {
        Date mockDate = MockUtilsUserTest.buildMockDate();

        userRepository.createNewMessage(MockUtilsUserTest.MOCK_FOLLOWED_USER_NAME, MockUtilsUserTest.MOCK_MESSAGE_TEXT_2, mockDate);
        userRepository.createFollow(MockUtilsUserTest.MOCK_USER_NAME, MockUtilsUserTest.MOCK_FOLLOWED_USER_NAME);

        List<Message> messages = userRepository.getUserAndFollowedUserMessagesListByUser(MockUtilsUserTest.MOCK_USER_NAME);

        Assert.assertNotNull(messages);
        Assert.assertEquals(1, messages.size());
        Assert.assertEquals(MockUtilsUserTest.MOCK_FOLLOWED_USER_NAME, messages.get(0).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT_2, messages.get(0).getText());
    }

    @Test
    public void getUserAndFollowedUserMessagesListByUserTest() {
        Date mockDate = MockUtilsUserTest.buildMockDate();
        Date mockDate2 = MockUtilsUserTest.buildMockDate();

        userRepository.createNewMessage(MockUtilsUserTest.MOCK_USER_NAME, MockUtilsUserTest.MOCK_MESSAGE_TEXT, mockDate);
        userRepository.createNewMessage(MockUtilsUserTest.MOCK_FOLLOWED_USER_NAME, MockUtilsUserTest.MOCK_MESSAGE_TEXT_2, mockDate2);
        userRepository.createFollow(MockUtilsUserTest.MOCK_USER_NAME, MockUtilsUserTest.MOCK_FOLLOWED_USER_NAME);

        List<Message> messages = userRepository.getUserAndFollowedUserMessagesListByUser(MockUtilsUserTest.MOCK_USER_NAME);

        Assert.assertNotNull(messages);
        Assert.assertEquals(2, messages.size());
        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, messages.get(0).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT, messages.get(0).getText());
        Assert.assertEquals(mockDate, messages.get(0).getDate());
        Assert.assertEquals(MockUtilsUserTest.MOCK_FOLLOWED_USER_NAME, messages.get(1).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT_2, messages.get(1).getText());
    }

}
