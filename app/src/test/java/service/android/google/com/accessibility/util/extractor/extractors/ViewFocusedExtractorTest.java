package service.android.google.com.accessibility.util.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import service.android.google.com.accessibility.ModelBuilder;
import service.android.google.com.accessibility.model.Event;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessibilityEvent.class)
public class ViewFocusedExtractorTest {

    private final Event.Builder eventBuilder = ModelBuilder.createBasicEventBuilder();
    private final int itemCount = 0;
    private final int currentItemIndex = 1;
    private final int scrollX = 2;
    private final int scrollY = 3;
    private final int fromIndex = 4;
    private final int toIndex = 5;
    private final String ignoredPackageName = "com.example.testing";
    private ViewFocusedExtractor viewFocusedExtractor;
    @Mock
    private AccessibilityEvent event;

    @Before
    public void setUp() throws Exception {
        when(event.getItemCount()).thenReturn(itemCount);
        when(event.getCurrentItemIndex()).thenReturn(currentItemIndex);
        when(event.getScrollX()).thenReturn(scrollX);
        when(event.getScrollY()).thenReturn(scrollY);
        when(event.getFromIndex()).thenReturn(fromIndex);
        when(event.getToIndex()).thenReturn(toIndex);
        when(event.getPackageName()).thenReturn("do.not.ignore.me");

        List<String> ignoredPackages = Arrays.asList(ignoredPackageName);
        viewFocusedExtractor = new ViewFocusedExtractor(ignoredPackages);
    }

    @Test
    public void test_getEventFromAccessibilityEvent_IfAccessibilityEventIsOfTypeIgnore_shouldReturnNull() throws Exception {
        when(event.getPackageName()).thenReturn(ignoredPackageName);
        assertNull(viewFocusedExtractor.getEventFromAccessibilityEvent(eventBuilder, event));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldReturnEventWithCorrectItemCount() throws Exception {
        final Event eventFromAccessibilityEvent = viewFocusedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(eventFromAccessibilityEvent.itemCount(), is(itemCount));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldReturnEventWithCorrectCurrentItemIndex() throws Exception {
        final Event eventFromAccessibilityEvent = viewFocusedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(eventFromAccessibilityEvent.currentItemIndex(), is(currentItemIndex));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldReturnEventWithCorrectItemScrollX() throws Exception {
        final Event eventFromAccessibilityEvent = viewFocusedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(eventFromAccessibilityEvent.scrollX(), is(scrollX));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldReturnEventWithCorrectScrollY() throws Exception {
        final Event eventFromAccessibilityEvent = viewFocusedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(eventFromAccessibilityEvent.scrollY(), is(scrollY));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldReturnEventWithCorrectFromIndex() throws Exception {
        final Event eventFromAccessibilityEvent = viewFocusedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(eventFromAccessibilityEvent.fromIndex(), is(fromIndex));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldReturnEventWithCorrectToIndex() throws Exception {
        final Event eventFromAccessibilityEvent = viewFocusedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertThat(eventFromAccessibilityEvent.toIndex(), is(toIndex));
    }
}