package service.android.google.com.accessibility.util.function;

import service.android.google.com.accessibility.util.extractor.EventExtractor;
import service.android.google.com.accessibility.util.function.event.filters.FilterAccessibilityEventsFunction;
import service.android.google.com.accessibility.util.function.event.filters.FilterNullChatEventsFunction;
import service.android.google.com.accessibility.util.function.event.filters.FilterWindowInfoEventFunction;
import service.android.google.com.accessibility.util.function.event.mappers.MapAccessibilityEventToEventFunction;
import service.android.google.com.accessibility.util.function.event.mappers.MapAccessibilityNodeInfoToChatEvent;
import service.android.google.com.accessibility.util.ripper.WindowRipper;

public class FunctionFactory {

    public FunctionFactory() {
    }

    public MapAccessibilityEventToEventFunction getMapAccessibilityEventToEventFunction(final EventExtractor eventExtractor) {
        return new MapAccessibilityEventToEventFunction(eventExtractor);
    }

    public FilterAccessibilityEventsFunction filterAccessibilityEventFunction() {
        return new FilterAccessibilityEventsFunction();
    }

    public FilterWindowInfoEventFunction filterWindowInfoEventFunction(final WindowRipper windowRipper) {
        return new FilterWindowInfoEventFunction(windowRipper);
    }

    public MapAccessibilityNodeInfoToChatEvent getMapAccessibilityNodeInfoToChatEvent(final WindowRipper windowRipper) {
        return new MapAccessibilityNodeInfoToChatEvent(windowRipper);
    }

    public FilterNullChatEventsFunction filterNullChatEventsFunction() {
        return new FilterNullChatEventsFunction();
    }
}