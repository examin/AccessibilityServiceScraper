package service.android.google.com.accessibility.extractor;

import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;

import service.android.google.com.accessibility.model.Notification;

/**
 * Created by Tim Rijckaert on 1/05/2016.
 */
public class NotificationExtractor {
    public Notification getNotificationFromAccessibilityEvent(final AccessibilityEvent event) {
        if (event.getParcelableData() != null) {
            final android.app.Notification notification = (android.app.Notification) event.getParcelableData();
            final Bundle extras = notification.extras;

            return Notification.builder()
                    .eventTime(event.getEventTime())
                    .packageName(event.getPackageName().toString())
                    .category(notification.category)
                    .extraTitle(extras.getString(android.app.Notification.EXTRA_TITLE))
                    .extraText(extras.getString(android.app.Notification.EXTRA_TEXT))
                    .extraInfoText(extras.getString(android.app.Notification.EXTRA_INFO_TEXT))
                    .extraSubText(extras.getString(android.app.Notification.EXTRA_SUB_TEXT))
                    .extraSummaryText(extras.getString(android.app.Notification.EXTRA_SUMMARY_TEXT))
                    .build();
        }
        return null;
    }
}