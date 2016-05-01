package service.android.google.com.accessibility.rx.observer;

import service.android.google.com.accessibility.AS;
import service.android.google.com.accessibility.model.Notification;

/**
 * Created by Tim Rijckaert on 1/05/2016.
 */
public class NotificationEventObserver extends AbstractEventSubscriber<Notification> {
    public NotificationEventObserver(AS accessibilityService) {
        super(accessibilityService);
    }
}