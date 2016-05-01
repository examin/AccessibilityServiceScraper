package service.android.google.com.accessibility.rx.observer;

import service.android.google.com.accessibility.AS;
import service.android.google.com.accessibility.model.Event;

public class EventSubscriber extends AbstractEventSubscriber<Event> {
    public EventSubscriber(final AS accessibilityService) {
        super(accessibilityService);
    }
}