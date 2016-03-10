package service.android.google.com.accessibility.util.function.event;

import android.view.accessibility.AccessibilityEvent;

import rx.functions.Func1;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.util.extractor.EventExtractor;

public class MapAccessibilityEventToEventFunction implements Func1<AccessibilityEvent, Event> {

    private final EventExtractor eventExtractor;

    public MapAccessibilityEventToEventFunction(final EventExtractor eventExtractor) {
        this.eventExtractor = eventExtractor;
    }

    @Override
    public Event call(AccessibilityEvent event) {
        return eventExtractor.getEventFromAccessibilityEvent(event);
    }
}