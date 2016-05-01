package service.android.google.com.accessibility.extractor;

import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import service.android.google.com.accessibility.model.Notification;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

/**
 * Created by Tim Rijckaert on 1/05/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessibilityEvent.class)
public class NotificationExtractorTest {

    private NotificationExtractor notificationExtractor;

    @Mock
    private AccessibilityEvent event;
    @Mock
    private android.app.Notification notification;
    @Mock
    private Bundle notificationExtras;

    private long eventTime = 1123;
    private String eventPackage = "com.testing";
    private String category = "category";
    private String extraTitle = "notification_title";
    private String extaText = "extra_text";
    private String extraInfoText = "extra_info_text";
    private String extraSubText = "extra_sub_text";
    private String extraSummaryText = "extra_summary_text";

    @Before
    public void setUp() throws Exception {
        when(event.getPackageName()).thenReturn(eventPackage);
        when(event.getEventTime()).thenReturn(eventTime);

        notification.extras = notificationExtras;
        notification.category = category;

        when(notificationExtras.getString(android.app.Notification.EXTRA_TITLE)).thenReturn(extraTitle);
        when(notificationExtras.getString(android.app.Notification.EXTRA_TEXT)).thenReturn(extaText);
        when(notificationExtras.getString(android.app.Notification.EXTRA_INFO_TEXT)).thenReturn(extraInfoText);
        when(notificationExtras.getString(android.app.Notification.EXTRA_SUB_TEXT)).thenReturn(extraSubText);
        when(notificationExtras.getString(android.app.Notification.EXTRA_SUMMARY_TEXT)).thenReturn(extraSummaryText);

        notificationExtractor = new NotificationExtractor();
    }

    @Test
    public void test_getNotificationFromAccessibilityEvent_shouldReturnNull_IfNoParcelableDataWasFound() throws Exception {
        when(event.getParcelableData()).thenReturn(null);
        assertNull(notificationExtractor.getNotificationFromAccessibilityEvent(event));
    }

    @Test
    public void test_getNotificationFromAccessibilityEvent_shouldReturnNotification_eventTime() throws Exception {
        when(event.getParcelableData()).thenReturn(notification);
        final Notification notification = notificationExtractor.getNotificationFromAccessibilityEvent(event);
        assertThat(notification.eventTime(), is(eventTime));
    }

    @Test
    public void test_getNotificationFromAccessibilityEvent_shouldReturnNotification_eventPackage() throws Exception {
        when(event.getParcelableData()).thenReturn(notification);
        final Notification notification = notificationExtractor.getNotificationFromAccessibilityEvent(event);
        assertThat(notification.packageName(), is(eventPackage));
    }

    @Test
    public void test_getNotificationFromAccessibilityEvent_shouldReturnNotification_category() throws Exception {
        when(event.getParcelableData()).thenReturn(notification);
        final Notification notification = notificationExtractor.getNotificationFromAccessibilityEvent(event);
        assertThat(notification.category(), is(category));
    }

    @Test
    public void test_getNotificationFromAccessibilityEvent_shouldHaveCorrectTitle() throws Exception {
        when(event.getParcelableData()).thenReturn(notification);
        final Notification notification = notificationExtractor.getNotificationFromAccessibilityEvent(event);
        assertThat(notification.extraTitle(), is(extraTitle));
    }

    @Test
    public void test_getNotificationFromAccessibilityEvent_shouldHaveCorrectExtraText() throws Exception {
        when(event.getParcelableData()).thenReturn(notification);
        final Notification notification = notificationExtractor.getNotificationFromAccessibilityEvent(event);
        assertThat(notification.extraTitle(), is(extraTitle));
    }

    @Test
    public void test_getNotificationFromAccessibilityEvent_shouldHaveCorrectExtraInfoText() throws Exception {
        when(event.getParcelableData()).thenReturn(notification);
        final Notification notification = notificationExtractor.getNotificationFromAccessibilityEvent(event);
        assertThat(notification.extraTitle(), is(extraTitle));
    }

    @Test
    public void test_getNotificationFromAccessibilityEvent_shouldHaveCorrectExtraSubText() throws Exception {
        when(event.getParcelableData()).thenReturn(notification);
        final Notification notification = notificationExtractor.getNotificationFromAccessibilityEvent(event);
        assertThat(notification.extraTitle(), is(extraTitle));
    }

    @Test
    public void test_getNotificationFromAccessibilityEvent_shouldHaveCorrectExtraSummaryText() throws Exception {
        when(event.getParcelableData()).thenReturn(notification);
        final Notification notification = notificationExtractor.getNotificationFromAccessibilityEvent(event);
        assertThat(notification.extraTitle(), is(extraTitle));
    }
}