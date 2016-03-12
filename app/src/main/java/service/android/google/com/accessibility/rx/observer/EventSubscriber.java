package service.android.google.com.accessibility.rx.observer;

import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.model.Event;

public class EventSubscriber extends AbstractEventSubscriber<Event> {
    public EventSubscriber(final AccessibilityServiceController accessibilityServiceController) {
        super(accessibilityServiceController);
    }
}
