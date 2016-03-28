package service.android.google.com.accessibility.util.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.model.Event;

import static org.mockito.Mockito.verify;

/**
 * Created by tim on 28.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class SaveEventToDbFunctionTest {

    private SaveEventToDbFunction saveEventToDbFunction;

    @Mock
    private RxDatabase rxDatabase;

    @Before
    public void setUp() throws Exception {
        saveEventToDbFunction = new SaveEventToDbFunction(rxDatabase);
    }

    @Test
    public void testCall_verifyEventType() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).eventType();
    }

    @Test
    public void testCall_verifyClassName() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).className();
    }

    @Test
    public void testCall_verifyEventTime() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).eventTime();
    }

    @Test
    public void testCall_verifyEventText() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).text();
    }

    @Test
    public void testCall_verifyIsEnabled() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).isEnabled();
    }

    @Test
    public void testCall_verifyIsPassword() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).isPassword();
    }

    @Test
    public void testCall_verifyIsChecked() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).isChecked();
    }

    @Test
    public void testCall_verifyFromIndex() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).fromIndex();
    }

    @Test
    public void testCall_verifyToIndex() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).toIndex();
    }

    @Test
    public void testCall_verifyAddedCount() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).addedCount();
    }

    @Test
    public void testCall_verifyRemovedCount() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).removedCount();
    }

    @Test
    public void testCall_verifyItemCount() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).itemCount();
    }

    @Test
    public void testCall_verifyBeforeText() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).beforeText();
    }

    @Test
    public void testCall_verifyContentDescription() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).contentDescription();
    }

    @Test
    public void testCall_verifyScrollX() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).scrollX();
    }

    @Test
    public void testCall_verifyScrollY() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).scrollY();
    }

    @Test
    public void testCall_verifyCurrentItemIndex() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verify(event).currentItemIndex();
    }
}