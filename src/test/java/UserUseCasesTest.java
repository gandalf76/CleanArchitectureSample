import data.model.user.Message;
import domain.UserRepository;
import domain.interactor.user.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import utils.MockUtilsUserTest;

import java.util.Date;
import java.util.List;

public class UserUseCasesTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    UserRepository mockUserRepository;

    @Captor
    ArgumentCaptor<String> userNameCaptor;

    @Captor
    ArgumentCaptor<String> textMessageCaptor;

    @Captor
    ArgumentCaptor<Date> dateCaptor;

    @Captor
    ArgumentCaptor<String> followedUserNameCaptor;

    @Test
    public void createMessageUseCaseTest() {
        Date mockDate = MockUtilsUserTest.buildMockDate();

        BaseUserUseCase useCase = new CreateMessageUseCase(MockUtilsUserTest.MOCK_USER_NAME, MockUtilsUserTest.MOCK_MESSAGE_TEXT, mockDate, mockUserRepository);
        List<Message> messages = useCase.execute();

        Mockito.verify(mockUserRepository, Mockito.times(1))
                .createNewMessage(userNameCaptor.capture(), textMessageCaptor.capture(), dateCaptor.capture());

        Assert.assertNotNull(messages);
        Assert.assertEquals(0, messages.size());
        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, userNameCaptor.getValue());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT, textMessageCaptor.getValue());
        Assert.assertEquals(mockDate, dateCaptor.getValue());
    }

    @Test
    public void getMessageListUseCase() {
        Date mockDate = MockUtilsUserTest.buildMockDate();
        List<Message> mockMessageList =  MockUtilsUserTest.buildMockMessageList(mockDate);
        Mockito.when(mockUserRepository.getMessagesListByUser(ArgumentMatchers.anyString())).thenReturn(mockMessageList);

        BaseUserUseCase useCase = new GetMessagesListUseCase(MockUtilsUserTest.MOCK_USER_NAME, mockUserRepository);
        List<Message> messages = useCase.execute();

        Mockito.verify(mockUserRepository, Mockito.times(1))
                .getMessagesListByUser(userNameCaptor.capture());

        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, userNameCaptor.getValue());

        Assert.assertNotNull(messages);
        Assert.assertEquals(1, messages.size());
        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, messages.get(0).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT, messages.get(0).getText());
        Assert.assertEquals(mockDate, messages.get(0).getDate());
    }

    @Test
    public void createFollowTest() {
        BaseUserUseCase useCase = new CreateFollowUseCase(MockUtilsUserTest.MOCK_USER_NAME, MockUtilsUserTest. MOCK_FOLLOWED_USER_NAME, mockUserRepository);
        List<Message> messages = useCase.execute();

        Mockito.verify(mockUserRepository, Mockito.times(1))
                .createFollow(userNameCaptor.capture(), followedUserNameCaptor.capture());

        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, userNameCaptor.getValue());
        Assert.assertEquals(MockUtilsUserTest.MOCK_FOLLOWED_USER_NAME, followedUserNameCaptor.getValue());

        Assert.assertNotNull(messages);
        Assert.assertEquals(0, messages.size());
    }

    @Test
    public void getWallUseCaseTest() {
        Date mockDate = MockUtilsUserTest.buildMockDate();
        Date mockDate2 = MockUtilsUserTest.buildMockDate();

        List<Message> mockMessageList = MockUtilsUserTest.buildMockMessageFollowedList(mockDate, mockDate2);

        Mockito.when(mockUserRepository.getUserAndFollowedUserMessagesListByUser(ArgumentMatchers.anyString()))
                .thenReturn(mockMessageList);

        BaseUserUseCase useCase = new GetWallUseCase(MockUtilsUserTest.MOCK_USER_NAME, mockUserRepository);
        List<Message> messages = useCase.execute();

        Mockito.verify(mockUserRepository, Mockito.times(1)).getUserAndFollowedUserMessagesListByUser(userNameCaptor.capture());
        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, userNameCaptor.getValue());

        Assert.assertNotNull(messages);
        Assert.assertEquals(2, messages.size());
        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, messages.get(0).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT, messages.get(0).getText());
        Assert.assertEquals(mockDate, messages.get(0).getDate());
        Assert.assertEquals(MockUtilsUserTest.MOCK_FOLLOWED_USER_NAME, messages.get(1).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT_2, messages.get(1).getText());
    }
}
