import data.CommandRepositoryImpl;
import data.model.command.BaseCommand;
import data.model.user.Message;
import domain.CommandRepository;
import domain.UserRepository;
import domain.interactor.user.BaseUserUseCase;
import domain.interactor.user.GetWallUseCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;
import utils.MockUtilsCommandTest;
import utils.MockUtilsUserTest;

import java.util.Date;
import java.util.List;

public class CommandRepositoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    UserRepository mockUserRepository;

    @Captor
    ArgumentCaptor<String> userNameCaptor;

    @Captor
    ArgumentCaptor<String> userFollowedNameCaptor;

    private CommandRepository commandRepository;

    @Before
    public void before() {
        commandRepository = new CommandRepositoryImpl();
    }

    @Test
    public void createPostCommandTest() {
        BaseCommand baseCommand = commandRepository.createPostCommand(MockUtilsCommandTest.MOCK_POST_COMMAND_TEXT, mockUserRepository);
        List<Message> messages = baseCommand.execute();

        Assert.assertNotNull(messages);
        Assert.assertEquals(0, messages.size());
    }

    @Test
    public void createReadCommandTest() {

        Date mockDate = MockUtilsUserTest.buildMockDate();

        Mockito.when(mockUserRepository.getMessagesListByUser(ArgumentMatchers.anyString())).thenReturn(MockUtilsUserTest.buildMockMessageList(mockDate));
        BaseCommand baseCommand = commandRepository.createReadCommand(MockUtilsCommandTest.MOCK_READ_COMMAND_TEXT, mockUserRepository);
        List<Message> messages = baseCommand.execute();

        Assert.assertNotNull(messages);
        Assert.assertEquals(1, messages.size());

        Mockito.verify(mockUserRepository, Mockito.times(1))
                .getMessagesListByUser(userNameCaptor.capture());
        Assert.assertEquals(MockUtilsCommandTest.MOCK_READ_COMMAND_TEXT, userNameCaptor.getValue());

        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, messages.get(0).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT, messages.get(0).getText());
        Assert.assertEquals(mockDate, messages.get(0).getDate());
    }

    @Test
    public void createFollowCommandTest() {

        Mockito.doAnswer(invocationOnMock -> null)
                .when(mockUserRepository).createFollow(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());

        BaseCommand command = commandRepository.createFollowCommand(MockUtilsCommandTest.MOCK_FOLLOW_COMMAND_TEXT, mockUserRepository);
        List<Message> messages = command.execute();

        Mockito.verify(mockUserRepository, Mockito.times(1)).createFollow(userNameCaptor.capture(), userFollowedNameCaptor.capture());

        Assert.assertNotNull(messages);
        Assert.assertEquals(0, messages.size());
        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, userNameCaptor.getValue());
        Assert.assertEquals(MockUtilsUserTest.MOCK_FOLLOWED_USER_NAME, userFollowedNameCaptor.getValue());
    }

    @Test
    public void createWallCommandTest() {
        Date mockDate = MockUtilsUserTest.buildMockDate();
        Date mockDate2 = MockUtilsUserTest.buildMockDate();
        List<Message> mockMessageList = MockUtilsUserTest.buildMockMessageFollowedList(mockDate, mockDate2);

        Mockito.when(mockUserRepository.getUserAndFollowedUserMessagesListByUser(ArgumentMatchers.anyString()))
                .thenReturn(mockMessageList);

        BaseCommand command = commandRepository.createWallCommand(MockUtilsCommandTest.MOCK_WALL_COMMAND_TEXT, mockUserRepository);
        List<Message> messages = command.execute();

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
