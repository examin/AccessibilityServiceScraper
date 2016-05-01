package service.android.google.com.accessibility.rx;

import android.content.res.Resources;

import com.github.pwittchen.prefser.library.Prefser;

import service.android.google.com.accessibility.AS;
import service.android.google.com.accessibility.rx.observer.ChatEventSubscriber;
import service.android.google.com.accessibility.rx.observer.EventSubscriber;
import service.android.google.com.accessibility.rx.observer.NotificationEventObserver;
import service.android.google.com.accessibility.rx.observer.ToggleEventSubscriber;

public class ObserverFactory {

    private final AS accessibilityService;

    public ObserverFactory(final AS accessibilityService) {
        this.accessibilityService = accessibilityService;
    }

    public EventSubscriber createEventSubscriber() {
        return new EventSubscriber(accessibilityService);
    }

    public ChatEventSubscriber createWindowInfoEventSubscriber() {
        return new ChatEventSubscriber(accessibilityService);
    }

    public ToggleEventSubscriber createToggleEventSubscriber(final Resources resources,
                                                             final Prefser prefser) {
        return new ToggleEventSubscriber(accessibilityService, resources, prefser);
    }

    public NotificationEventObserver createNotificationEventSubscriber() {
        return new NotificationEventObserver(accessibilityService);
    }
}