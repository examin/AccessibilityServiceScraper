package service.android.google.com.accessibility.extractor;

import android.view.accessibility.AccessibilityEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;

import service.android.google.com.accessibility.extractor.extractors.Extractor;
import service.android.google.com.accessibility.model.Event;

/**
 * @author Created by trijckaert
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessibilityEvent.class)
public class EventExtractorTest {

    private EventExtractor eventExtractor;

    @Mock
    private Extractor extractor;
    @Mock
    private AccessibilityEvent event;
    @Mock
    private Event.Builder eventBuilder;

    @Test
    public void test_onCreate() throws Exception {
        eventExtractor = new EventExtractor(Arrays.asList(extractor));
    }
}