package service.android.google.com.accessibility.util.function.event.mappers;

import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import service.android.google.com.accessibility.ModelBuilder;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.util.extractor.EventExtractor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessibilityEvent.class)
public class MapAccessibilityEventToEventFunctionTest {

    private MapAccessibilityEventToEventFunction mapAccessibilityEventToEventFunction;

    @Mock
    private EventExtractor eventExtractor;
    @Mock
    private AccessibilityEvent event;

    @Before
    public void setUp() throws Exception {
        mapAccessibilityEventToEventFunction = new MapAccessibilityEventToEventFunction(eventExtractor);
    }

    @Test
    public void test_call() throws Exception {
        final Event mockEvent = ModelBuilder.createEvent();
        when(eventExtractor.getEventFromAccessibilityEvent(this.event)).thenReturn(mockEvent);
        assertThat(mapAccessibilityEventToEventFunction.call(event), is(mockEvent));
    }
}