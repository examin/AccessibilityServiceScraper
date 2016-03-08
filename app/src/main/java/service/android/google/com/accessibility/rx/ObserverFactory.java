package service.android.google.com.accessibility.rx;

import rx.Subscriber;
import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.rx.observer.EventSubscriber;

/**
 * Created by tim on 08.03.16.
 */
public class ObserverFactory {

    public ObserverFactory() {
    }

    public Subscriber<Event> createEventSubscriber(AccessibilityServiceController accessibilityServiceController) {
        return new EventSubscriber(accessibilityServiceController);
    }
}
