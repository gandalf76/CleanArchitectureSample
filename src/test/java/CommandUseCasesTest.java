import data.model.command.FollowCommand;
import data.model.command.PostCommand;
import data.model.command.ReadCommand;
import data.model.command.WallCommand;
import data.model.user.Message;
import domain.CommandRepository;
import domain.UserRepository;
import domain.interactor.command.*;
import domain.interactor.user.*;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommandUseCasesTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    CommandRepository mockCommandRepository;

    @Mock
    UserRepository mockUserRepository;

    @Captor
    ArgumentCaptor<String> userNameCaptor;

    @Test
    public void readCommandCaseTest() {
        Date mockDate = MockUtilsUserTest.buildMockDate();
        List<Message> mockMessageList = MockUtilsUserTest.buildMockMessageList(mockDate);
        ReadCommand readCommand = Mockito.spy(new ReadCommand(MockUtilsUserTest.MOCK_USER_NAME, mockUserRepository));

        Mockito.when(mockCommandRepository.createReadCommand(MockUtilsUserTest.MOCK_USER_NAME, mockUserRepository))
                .thenReturn(readCommand);
        Mockito.when(mockUserRepository.getMessagesListByUser(MockUtilsUserTest.MOCK_USER_NAME))
                .thenReturn(mockMessageList);

        BaseCommandUseCase useCase = new ReadCommandUseCase(MockUtilsUserTest.MOCK_USER_NAME, mockUserRepository, mockCommandRepository);
        List<Message> messages = useCase.execute();

        Mockito.verify(readCommand, Mockito.times(1)).execute();

        Assert.assertNotNull(messages);
        Assert.assertEquals(1, messages.size());
        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, messages.get(0).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT, messages.get(0).getText());
        Assert.assertEquals(mockDate, messages.get(0).getDate());
    }

    @Test
    public void postCommandUseCase() {
        Date mockDate = MockUtilsUserTest.buildMockDate();
        PostCommand postCommand = Mockito.spy(new PostCommand(MockUtilsCommandTest.MOCK_POST_COMMAND_TEXT, mockUserRepository));

        Mockito.when(mockCommandRepository.createPostCommand(MockUtilsCommandTest.MOCK_POST_COMMAND_TEXT, mockUserRepository))
                .thenReturn(postCommand);
        Mockito.doAnswer(invocationOnMock -> null)
                .when(mockUserRepository)
                .createNewMessage(MockUtilsUserTest.MOCK_USER_NAME, MockUtilsUserTest.MOCK_USER_NAME, mockDate);

        BaseCommandUseCase useCase = new PostCommandUseCase(MockUtilsCommandTest.MOCK_POST_COMMAND_TEXT, mockUserRepository, mockCommandRepository);
        List<Message> messages = useCase.execute();

        Mockito.verify(postCommand, Mockito.times(1)).execute();

        Assert.assertNotNull(messages);
        Assert.assertEquals(0, messages.size());
    }

    @Test
    public void followCommandUseCaseTest() {
        FollowCommand followCommand = Mockito.spy(new FollowCommand(MockUtilsCommandTest.MOCK_FOLLOW_COMMAND_TEXT, mockUserRepository));

        Mockito.when(mockCommandRepository.createFollowCommand(MockUtilsCommandTest.MOCK_FOLLOW_COMMAND_TEXT, mockUserRepository))
                .thenReturn(followCommand);
        Mockito.doAnswer(invocationOnMock -> null)
                .when(mockUserRepository)
                .createFollow(MockUtilsUserTest.MOCK_USER_NAME, MockUtilsUserTest.MOCK_FOLLOWED_USER_NAME);

        BaseCommandUseCase useCase = new FollowCommandUseCase(MockUtilsCommandTest.MOCK_FOLLOW_COMMAND_TEXT, mockUserRepository, mockCommandRepository);
        List<Message> messages = useCase.execute();

        Mockito.verify(followCommand, Mockito.times(1)).execute();

        Assert.assertNotNull(messages);
        Assert.assertEquals(0, messages.size());
    }

    @Test
    public void getWallUseCaseTest() {
        Date mockDate = MockUtilsUserTest.buildMockDate();
        Date mockDate2 = MockUtilsUserTest.buildMockDate();

        List<Message> mockMessageList = MockUtilsUserTest.buildMockMessageFollowedList(mockDate, mockDate2);
        WallCommand wallCommand = Mockito.spy(new WallCommand(MockUtilsCommandTest.MOCK_WALL_COMMAND_TEXT, mockUserRepository));

        Mockito.when(mockCommandRepository.createWallCommand(MockUtilsUserTest.MOCK_USER_NAME, mockUserRepository))
                .thenReturn(wallCommand);
        Mockito.when(mockUserRepository.getUserAndFollowedUserMessagesListByUser(MockUtilsUserTest.MOCK_USER_NAME))
                .thenReturn(mockMessageList);

        BaseCommandUseCase useCase = new WallCommandUseCase(MockUtilsUserTest.MOCK_USER_NAME, mockUserRepository, mockCommandRepository);
        List<Message> messages = useCase.execute();

        Mockito.verify(wallCommand, Mockito.times(1)).execute();

        Assert.assertNotNull(messages);
        Assert.assertEquals(2, messages.size());
        Assert.assertEquals(MockUtilsUserTest.MOCK_USER_NAME, messages.get(0).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT, messages.get(0).getText());
        Assert.assertEquals(mockDate, messages.get(0).getDate());
        Assert.assertEquals(MockUtilsUserTest.MOCK_FOLLOWED_USER_NAME, messages.get(1).getUserName());
        Assert.assertEquals(MockUtilsUserTest.MOCK_MESSAGE_TEXT_2, messages.get(1).getText());
    }
}
