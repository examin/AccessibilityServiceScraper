package service.android.google.com.accessibility.util.function;

import service.android.google.com.accessibility.extractor.EventExtractor;
import service.android.google.com.accessibility.scraper.WindowRipper;
import service.android.google.com.accessibility.util.function.event.filters.FilterNullChatEventsFunction;
import service.android.google.com.accessibility.util.function.event.filters.FilterUnnecessaryAccessibilityEventsFunction;
import service.android.google.com.accessibility.util.function.event.filters.FilterWindowInfoEventFunction;
import service.android.google.com.accessibility.util.function.event.mappers.MapAccessibilityEventToEventFunction;
import service.android.google.com.accessibility.util.function.event.mappers.MapAccessibilityNodeInfoToChatEvent;

public class FunctionFactory {

    public FunctionFactory() {
    }

    public MapAccessibilityEventToEventFunction getMapAccessibilityEventToEventFunction(final EventExtractor eventExtractor) {
        return new MapAccessibilityEventToEventFunction(eventExtractor);
    }

    public FilterUnnecessaryAccessibilityEventsFunction filterAccessibilityEventFunction() {
        return new FilterUnnecessaryAccessibilityEventsFunction();
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