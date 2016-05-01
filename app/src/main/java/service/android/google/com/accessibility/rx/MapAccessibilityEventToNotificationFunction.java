package service.android.google.com.accessibility.rx;

import android.view.accessibility.AccessibilityEvent;

import rx.functions.Func1;
import service.android.google.com.accessibility.extractor.NotificationExtractor;

/**
 * Created by Tim Rijckaert on 1/05/2016.
 */
public class MapAccessibilityEventToNotificationFunction implements Func1<AccessibilityEvent, service.android.google.com.accessibility.model.Notification> {

    private final NotificationExtractor notificationExtractor;

    public MapAccessibilityEventToNotificationFunction(final NotificationExtractor notificationExtractor) {
        this.notificationExtractor = notificationExtractor;
    }

    @Override
    public service.android.google.com.accessibility.model.Notification call(final AccessibilityEvent event) {
        return notificationExtractor.getNotificationFromAccessibilityEvent(event);
    }
}