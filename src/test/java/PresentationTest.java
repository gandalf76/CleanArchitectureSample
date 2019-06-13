import data.model.user.Message;
import domain.interactor.command.BaseCommandUseCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import presentation.mapper.Mapper;
import presentation.model.ConsoleMessage;
import utils.MockUtilsUserTest;

import java.util.Date;

public class PresentationTest {

    private Mapper mapper;

    @Before
    public void setUp() {
        this.mapper = new Mapper();
    }

    @Test
    public void consoleMessageReadTest() {
        Date mockDate = MockUtilsUserTest.buildMockDate();
        Message mockMessage = MockUtilsUserTest.buildMessage(mockDate);

        Mapper mapper = new Mapper();
        ConsoleMessage consoleMessage = mapper.asUIModel(mockMessage, BaseCommandUseCase.Type.READ);

        Assert.assertNotNull(consoleMessage);
        Assert.assertEquals(String.format("%s (%s)", mockMessage.getText(), mockMessage.getDate()), consoleMessage.getText());
    }


    @Test
    public void consoleMessagePostTest() {
        Date mockDate = MockUtilsUserTest.buildMockDate();
        Message mockMessage = MockUtilsUserTest.buildMessage(mockDate);

        Mapper mapper = new Mapper();
        ConsoleMessage consoleMessage = mapper.asUIModel(mockMessage, BaseCommandUseCase.Type.POST);

        Assert.assertNotNull(consoleMessage);
        Assert.assertEquals(mockMessage.getText(), consoleMessage.getText());
    }


    @Test
    public void consoleMessageFollowTest() {
        Date mockDate = MockUtilsUserTest.buildMockDate();
        Message mockMessage = MockUtilsUserTest.buildMessage(mockDate);

        Mapper mapper = new Mapper();
        ConsoleMessage consoleMessage = mapper.asUIModel(mockMessage, BaseCommandUseCase.Type.FOLLOW);

        Assert.assertNotNull(consoleMessage);
        Assert.assertEquals(mockMessage.getText(), consoleMessage.getText());
    }


    @Test
    public void consoleMessageWallTest() {
        Date mockDate = MockUtilsUserTest.buildMockDate();
        Message mockMessage = MockUtilsUserTest.buildMessage(mockDate);

        Mapper mapper = new Mapper();
        ConsoleMessage consoleMessage = mapper.asUIModel(mockMessage, BaseCommandUseCase.Type.WALL);

        Assert.assertNotNull(consoleMessage);
        Assert.assertEquals(String.format("%s - %s (%s)", mockMessage.getUserName(), mockMessage.getText(), mockMessage.getDate()), consoleMessage.getText());
    }
}
