package service.android.google.com.accessibility.rx;

import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import service.android.google.com.accessibility.extractor.NotificationExtractor;

import static org.mockito.Mockito.verify;

/**
 * Created by Tim Rijckaert on 1/05/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessibilityEvent.class)
public class MapAccessibilityEventToNotificationFunctionTest {

    private MapAccessibilityEventToNotificationFunction mapAccessibilityEventToNotificationFunction;

    @Mock
    private NotificationExtractor notificationExtractor;
    @Mock
    private AccessibilityEvent event;

    @Before
    public void setUp() throws Exception {
        mapAccessibilityEventToNotificationFunction = new MapAccessibilityEventToNotificationFunction(
                notificationExtractor
        );
    }

    @Test
    public void test_call() throws Exception {
        mapAccessibilityEventToNotificationFunction.call(event);
        verify(notificationExtractor).getNotificationFromAccessibilityEvent(event);
    }
}