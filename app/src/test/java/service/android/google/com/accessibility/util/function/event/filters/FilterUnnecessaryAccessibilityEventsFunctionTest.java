package service.android.google.com.accessibility.util.function.event.filters;

import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.model.Event;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
public class FilterUnnecessaryAccessibilityEventsFunctionTest {

    private FilterUnnecessaryAccessibilityEventsFunction filterUnnecessaryAccessibilityEventsFunction;

    private String emptyTextString = "";

    @Mock
    private Event event;

    @Before
    public void setUp() throws Exception {
        when(event.text()).thenReturn(emptyTextString);
        filterUnnecessaryAccessibilityEventsFunction = new FilterUnnecessaryAccessibilityEventsFunction();
    }

    @Test
    public void testCall_eventIsNull_shouldReturnFalse() throws Exception {
        assertFalse(filterUnnecessaryAccessibilityEventsFunction.call(null));
    }

    @Test
    public void testCall_eventIsOfType_TYPE_NOTIFICATION_STATE_CHANGED_shouldReturnTrue() throws Exception {
        when(event.eventType()).thenReturn(AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED);
        assertTrue(filterUnnecessaryAccessibilityEventsFunction.call(event));
    }

    @Test
    public void test_eventIsOfType_TYPE_VIEW_FOCUSED_shouldReturnTrue() throws Exception {
        when(event.eventType()).thenReturn(AccessibilityEvent.TYPE_VIEW_FOCUSED);
        assertTrue(filterUnnecessaryAccessibilityEventsFunction.call(event));
    }

    @Test
    public void test_eventHasText_shouldReturnTrue() throws Exception {
        when(event.text()).thenReturn("non empty text");
        assertTrue(filterUnnecessaryAccessibilityEventsFunction.call(event));
    }

    @Test
    public void test_eventIsOfUnknownType_shouldReturnFalse() throws Exception {
        when(event.eventType()).thenReturn(235675432);
        assertFalse(filterUnnecessaryAccessibilityEventsFunction.call(event));
    }
}