package service.android.google.com.accessibility.util.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.Arrays;

import service.android.google.com.accessibility.ModelBuilder;
import service.android.google.com.accessibility.model.Event;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(AccessibilityEvent.class)
public class WindowStateChangedExtractorTest {

    private final String ignorePackageName = "com.testing.ignore.me";
    private final Event.Builder eventBuilder = ModelBuilder.createBasicEventBuilder();
    private WindowStateChangedExtractor windowStateChangedExtractor;
    @Mock
    private AccessibilityEvent event;

    @Before
    public void setUp() throws Exception {
        when(event.getPackageName()).thenReturn("do.not.ignore.me");
        windowStateChangedExtractor = new WindowStateChangedExtractor(Arrays.asList(ignorePackageName));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldReturnNullIfWasOfTypePackageToIgnore() throws Exception {
        when(event.getPackageName()).thenReturn(ignorePackageName);
        assertNull(windowStateChangedExtractor.getEventFromAccessibilityEvent(eventBuilder, event));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldReturnNonNullValue() throws Exception {
        assertNotNull(windowStateChangedExtractor.getEventFromAccessibilityEvent(eventBuilder, event));
    }
}