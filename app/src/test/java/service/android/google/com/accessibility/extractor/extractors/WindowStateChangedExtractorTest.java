package service.android.google.com.accessibility.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import com.github.pwittchen.prefser.library.Prefser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.HashSet;

import service.android.google.com.accessibility.ModelBuilder;
import service.android.google.com.accessibility.model.Event;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
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
    private String pref_key = "PREF_KEY";

    @Mock
    private AccessibilityEvent event;
    @Mock
    private Prefser prefser;
    @Mock
    private android.content.SharedPreferences sharedPrefs;

    @Before
    public void setUp() throws Exception {
        when(prefser.getPreferences()).thenReturn(sharedPrefs);
        when(sharedPrefs.getStringSet(eq("PREF_KEY"), any(HashSet.class))).thenReturn(new HashSet<>(asList(ignorePackageName)));

        windowStateChangedExtractor = new WindowStateChangedExtractor(pref_key, prefser);
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldReturnNullIfWasOfTypePackageToIgnore() throws Exception {
        when(event.getPackageName()).thenReturn(ignorePackageName);
        windowStateChangedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertNull(windowStateChangedExtractor.getEventFromAccessibilityEvent(eventBuilder, event));
    }

    @Test
    public void test_getEventFromAccessibilityEvent_shouldReturnNonNullValue() throws Exception {
        when(event.getPackageName()).thenReturn("com.testing.do.not.ignore.me");
        windowStateChangedExtractor.getEventFromAccessibilityEvent(eventBuilder, event);
        assertNotNull(windowStateChangedExtractor.getEventFromAccessibilityEvent(eventBuilder, event));
    }
}