package service.android.google.com.accessibility.util.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import service.android.google.com.accessibility.ModelBuilder;
import service.android.google.com.accessibility.model.Event;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessibilityEvent.class)
public class ViewTextChangedExtractorTest {

    private final int fromIndex = 0;
    private final int addedCount = 1;
    private final int removedCount = 2;
    private final String beforeText = "beforeText";
    private ViewTextChangedExtractor viewTextChangedExtractor;
    private Event.Builder eventBuilder = ModelBuilder.createBasicEventBuilder();
    @Mock
    private AccessibilityEvent event;

    @Before
    public void setUp() throws Exception {
        when(event.getFromIndex()).thenReturn(fromIndex);
        when(event.getAddedCount()).thenReturn(addedCount);
        when(event.getRemovedCount()).thenReturn(removedCount);
        when(event.getBeforeText()).thenReturn(beforeText);

        viewTextChangedExtractor = new ViewTextChangedExtractor();
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldSetCorrectFromIndex() throws Exception {
        final Event buildEvent = viewTextChangedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.fromIndex(), is(fromIndex));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldSetCorrectAddedCount() throws Exception {
        final Event buildEvent = viewTextChangedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.addedCount(), is(addedCount));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldSetCorrectRemovedCount() throws Exception {
        final Event buildEvent = viewTextChangedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.removedCount(), is(removedCount));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldSetCorrectBeforeText() throws Exception {
        final Event buildEvent = viewTextChangedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(buildEvent.beforeText(), is(beforeText));
    }
}