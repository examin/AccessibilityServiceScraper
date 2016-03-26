package service.android.google.com.accessibility.rx;

import rx.Subscriber;
import service.android.google.com.accessibility.AS;
import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.rx.observer.ChatEventSubscriber;
import service.android.google.com.accessibility.rx.observer.EventSubscriber;

public class ObserverFactory {

    private final AS accessibilityService;

    public ObserverFactory(final AS accessibilityService) {
        this.accessibilityService = accessibilityService;
    }

    public Subscriber<Event> createEventSubscriber() {
        return new EventSubscriber(accessibilityService);
    }

    public Subscriber<ChatEvent> createWindowInfoEventSubscriber() {
        return new ChatEventSubscriber(accessibilityService);
    }
}