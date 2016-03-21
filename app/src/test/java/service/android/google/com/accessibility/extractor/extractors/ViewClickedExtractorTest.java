package service.android.google.com.accessibility.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import service.android.google.com.accessibility.ModelBuilder;
import service.android.google.com.accessibility.model.Event;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessibilityEvent.class)
public class ViewClickedExtractorTest {

    private final int scrollX = 0;
    private final int scrollY = 1;
    private final int fromIndex = 2;
    private final int toIndex = 3;
    private final int itemCount = 4;
    private final int addedCount = 5;
    private final int removedCount = 6;
    private final String beforeText = "beforeText";
    private final Event.Builder eventBuilder = ModelBuilder.createBasicEventBuilder();
    private ViewClickedExtractor viewClickedExtractor;
    @Mock
    private AccessibilityEvent event;

    @Before
    public void setUp() throws Exception {
        when(event.getScrollX()).thenReturn(scrollX);
        when(event.getScrollY()).thenReturn(scrollY);
        when(event.getFromIndex()).thenReturn(fromIndex);
        when(event.getToIndex()).thenReturn(toIndex);
        when(event.getItemCount()).thenReturn(itemCount);
        when(event.getAddedCount()).thenReturn(addedCount);
        when(event.getRemovedCount()).thenReturn(removedCount);
        when(event.getBeforeText()).thenReturn(beforeText);

        viewClickedExtractor = new ViewClickedExtractor();
    }

    @Test
    public void test_getEventFromAccessibilityEvent_hasCorrectScrollXValue() throws Exception {
        final Event buildEvent = viewClickedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.scrollX(), is(scrollX));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_hasCorrectScrollYValue() throws Exception {
        final Event buildEvent = viewClickedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.scrollY(), is(scrollY));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_hasCorrectFromIndexValue() throws Exception {
        final Event buildEvent = viewClickedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.fromIndex(), is(fromIndex));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_hasCorrectToIndexValue() throws Exception {
        final Event buildEvent = viewClickedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.toIndex(), is(toIndex));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_hasCorrectItemCountValue() throws Exception {
        final Event buildEvent = viewClickedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.itemCount(), is(itemCount));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_hasCorrectAddedCountValue() throws Exception {
        final Event buildEvent = viewClickedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.addedCount(), is(addedCount));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_hasCorrectRemovedCountValue() throws Exception {
        final Event buildEvent = viewClickedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.removedCount(), is(removedCount));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_hasCorrectBeforeTextValue() throws Exception {
        final Event buildEvent = viewClickedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.beforeText(), is(beforeText));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_hasCorrectBeforeTextValue_IfBeforeTextWasNull() throws Exception {
        when(event.getBeforeText()).thenReturn(null);
        final Event buildEvent = viewClickedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.beforeText(), is(""));
    }
}