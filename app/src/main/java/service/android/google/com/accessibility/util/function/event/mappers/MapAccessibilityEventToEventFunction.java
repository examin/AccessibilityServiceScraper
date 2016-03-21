package service.android.google.com.accessibility.util.function.event.mappers;

import android.view.accessibility.AccessibilityEvent;

import rx.functions.Func1;
import service.android.google.com.accessibility.extractor.EventExtractor;
import service.android.google.com.accessibility.model.Event;

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