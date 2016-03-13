package service.android.google.com.accessibility.util.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import service.android.google.com.accessibility.model.Event;

/**
 * Created by tim on 08.03.16.
 * Represents the event showing a Notification
 */
public class NotificationStateChangedExtractor extends AbstractEventExtractor {
    public NotificationStateChangedExtractor() {
        super(AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED);
    }

    @Override
    public Event getEventFromAccessibilityEvent(Event.Builder unfinishedBuilder, AccessibilityEvent event) {
        return unfinishedBuilder
                .notificationParcel(event.getParcelableData())
                .build();
    }
}
