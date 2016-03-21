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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessibilityEvent.class)
public class NotificationStateChangedExtractorTest {

    private NotificationStateChangedExtractor notificationStateChangedExtractor;

    private Event.Builder eventBuilder;

    @Mock
    private AccessibilityEvent event;
    @Mock
    private android.os.Parcelable parcelableNotificationData;

    @Before
    public void setUp() throws Exception {
        eventBuilder = ModelBuilder.createBasicEventBuilder();
        notificationStateChangedExtractor = new NotificationStateChangedExtractor();
    }

    @Test
    public void test_getEventFromAccessibilityEvent() throws Exception {
        when(event.getParcelableData()).thenReturn(parcelableNotificationData);
        assertThat(notificationStateChangedExtractor.getEventFromAccessibilityEvent(eventBuilder, event).notificationParcel(), is(parcelableNotificationData));
    }
}