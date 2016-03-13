package service.android.google.com.accessibility.rx;

import rx.Subscriber;
import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.rx.observer.ChatEventSubscriber;
import service.android.google.com.accessibility.rx.observer.EventSubscriber;

public class ObserverFactory {

    public ObserverFactory() {
    }

    public Subscriber<Event> createEventSubscriber(final AccessibilityServiceController controller) {
        return new EventSubscriber(controller);
    }

    public Subscriber<ChatEvent> createWindowInfoEventSubscriber(final AccessibilityServiceController controller) {
        return new ChatEventSubscriber(controller);
    }
}