package service.android.google.com.accessibility.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import service.android.google.com.accessibility.model.Event;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessibilityEvent.class)
public class AbstractEventExtractorTest {

    @Mock
    AccessibilityEvent event;
    private MockEventExtractor mockEventExtractor;
    private int accessibilityType;

    @Before
    public void setUp() throws Exception {
        accessibilityType = AccessibilityEvent.TYPE_VIEW_CONTEXT_CLICKED;
        mockEventExtractor = new MockEventExtractor(accessibilityType);
    }

    @Test
    public void test_isForAccessibilityEvent_shouldReturnFalseIfNotForAccessibilityType() throws Exception {
        prepareAccessibilityEvent(AccessibilityEvent.CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION);
        assertFalse(mockEventExtractor.isForAccessibilityEvent(event));
    }

    @Test
    public void test_isForAccessibilityEvent_shouldReturnTrueIfForAccessibilityType() throws Exception {
        prepareAccessibilityEvent(accessibilityType);
        assertTrue(mockEventExtractor.isForAccessibilityEvent(event));
    }

    private void prepareAccessibilityEvent(int accessibilityType) {
        when(event.getEventType()).thenReturn(accessibilityType);
    }

    class MockEventExtractor extends AbstractEventExtractor {
        public MockEventExtractor(int accessibilityEventType) {
            super(accessibilityEventType);
        }

        @Override
        public Event getEventFromAccessibilityEvent(Event.Builder unfinishedBuilder, AccessibilityEvent event) {
            return null;
        }
    }
}