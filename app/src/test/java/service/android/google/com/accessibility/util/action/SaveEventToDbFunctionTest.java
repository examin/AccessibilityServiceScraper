package service.android.google.com.accessibility.util.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.model.database.EventDTO;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by tim on 28.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class SaveEventToDbFunctionTest {

    private SaveEventToDbFunction saveEventToDbFunction;

    @Mock
    private RxDatabase rxDatabase;

    private String text = "text";
    private String packageName = "com.testing";
    private Integer eventType = 123;
    private String className = "com.testing.className";
    private Long eventTime = 125L;
    private Boolean isEnabled = true;
    private Boolean isPassword = true;
    private Boolean isChecked = true;
    private Integer fromIndex = 18;
    private Integer toIndex = 19;
    private Integer addedCount = 1;
    private Integer removeCount = 0;
    private Integer itemCount = 20;
    private String beforeText = "tex";
    private String contentDescription = "eventTesting";
    private Integer scrollX = 90;
    private Integer scrollY = 98;
    private Integer currentItemIndex = 5;

    private ArgumentCaptor<EventDTO> eventDTOArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        eventDTOArgumentCaptor = ArgumentCaptor.forClass(EventDTO.class);
        saveEventToDbFunction = new SaveEventToDbFunction(rxDatabase);
    }

    public Event prepareForTest() {
        final Event event = Mockito.mock(Event.class);
        when(event.packageName()).thenReturn(packageName);
        when(event.eventType()).thenReturn(eventType);
        when(event.className()).thenReturn(className);
        when(event.eventTime()).thenReturn(eventTime);
        when(event.text()).thenReturn(text);
        when(event.isEnabled()).thenReturn(isEnabled);
        when(event.isPassword()).thenReturn(isPassword);
        when(event.isChecked()).thenReturn(isChecked);
        when(event.fromIndex()).thenReturn(fromIndex);
        when(event.toIndex()).thenReturn(toIndex);
        when(event.addedCount()).thenReturn(addedCount);
        when(event.removedCount()).thenReturn(removeCount);
        when(event.itemCount()).thenReturn(itemCount);
        when(event.beforeText()).thenReturn(beforeText);
        when(event.contentDescription()).thenReturn(contentDescription);
        when(event.scrollX()).thenReturn(scrollX);
        when(event.scrollY()).thenReturn(scrollY);
        when(event.currentItemIndex()).thenReturn(currentItemIndex);
        return event;
    }

    @Test
    public void testCall_shouldNotSaveIfObjectWasNull() throws Exception {
        saveEventToDbFunction.call(null);
        verifyZeroInteractions(rxDatabase);
    }

    @Test
    public void testCall_shouldNotSaveIfEventHasNoText() throws Exception {
        final Event event = Mockito.mock(Event.class);
        saveEventToDbFunction.call(event);
        verifyZeroInteractions(rxDatabase);
    }

    @Test
    public void testCall_verifyEventType() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().getEventType(), is(eventType));
    }

    @Test
    public void testCall_verifyClassName() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().getClassName(), is(className));
    }

    @Test
    public void testCall_verifyEventTime() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().getEventTime(), is(eventTime));
    }

    @Test
    public void testCall_verifyEventText() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
    }

    @Test
    public void testCall_verifyIsEnabled() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().isEnabled(), is(isEnabled));
    }

    @Test
    public void testCall_verifyIsPassword() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().isPassword(), is(isPassword));
    }

    @Test
    public void testCall_verifyIsChecked() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().isChecked(), is(isChecked));
    }

    @Test
    public void testCall_verifyFromIndex() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().getFromIndex(), is(fromIndex));
    }

    @Test
    public void testCall_verifyToIndex() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().getToIndex(), is(toIndex));
    }

    @Test
    public void testCall_verifyAddedCount() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().getAddedCount(), is(addedCount));
    }

    @Test
    public void testCall_verifyRemovedCount() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().getRemovedCount(), is(removeCount));
    }

    @Test
    public void testCall_verifyItemCount() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().getItemCount(), is(itemCount));
    }

    @Test
    public void testCall_verifyBeforeText() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().isEnabled(), is(isEnabled));
    }

    @Test
    public void testCall_verifyContentDescription() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().getContentDescription(), is(contentDescription));
    }

    @Test
    public void testCall_verifyScrollX() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().getScrollX(), is(scrollX));
    }

    @Test
    public void testCall_verifyScrollY() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().getScrollY(), is(scrollY));
    }

    @Test
    public void testCall_verifyCurrentItemIndex() throws Exception {
        final Event event = prepareForTest();
        saveEventToDbFunction.call(event);
        verify(rxDatabase).put(eventDTOArgumentCaptor.capture());
        assertThat(eventDTOArgumentCaptor.getValue().getCurrentItemIndex(), is(currentItemIndex));
    }
}