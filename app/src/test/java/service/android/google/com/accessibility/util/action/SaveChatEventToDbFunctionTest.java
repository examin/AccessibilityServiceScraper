package service.android.google.com.accessibility.util.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.ModelBuilder;
import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.model.ChatMessage;
import service.android.google.com.accessibility.model.database.ChatMessageDTO;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by tim on 28.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class SaveChatEventToDbFunctionTest {

    private SaveChatEventToDbFunction saveChatEventToDbFunction;

    @Mock
    private RxDatabase rxDatabase;

    @Before
    public void setUp() throws Exception {
        saveChatEventToDbFunction = new SaveChatEventToDbFunction(rxDatabase);
    }

    @Test
    public void testCall_shouldNotSaveIfChatEventIsNull() throws Exception {
        saveChatEventToDbFunction.call(null);
        verifyZeroInteractions(rxDatabase);
    }

    @Test
    public void testCall_shouldNotSaveIfChatEventContainsNoChatMessages() throws Exception {
        ChatEvent chatEvent = mock(ChatEvent.class);
        saveChatEventToDbFunction.call(chatEvent);
        verifyZeroInteractions(rxDatabase);
    }

    @Test
    public void testSave_shouldPutSameAmountOfChatMessagesInDb() throws Exception {
        ChatEvent chatEvent = mock(ChatEvent.class);
        ChatMessage message = ModelBuilder.createMessage();
        when(chatEvent.messages()).thenReturn(asList(message, message, message, message));
        saveChatEventToDbFunction.call(chatEvent);
        verify(rxDatabase, times(4)).put(any(ChatMessageDTO.class));
    }
}