package service.android.google.com.accessibility.util.function;

import service.android.google.com.accessibility.extractor.EventExtractor;
import service.android.google.com.accessibility.extractor.NotificationExtractor;
import service.android.google.com.accessibility.rx.MapAccessibilityEventToNotificationFunction;
import service.android.google.com.accessibility.scraper.WindowRipper;
import service.android.google.com.accessibility.util.function.event.filters.FilterNullChatEventsFunction;
import service.android.google.com.accessibility.util.function.event.filters.FilterUnnecessaryAccessibilityEventsFunction;
import service.android.google.com.accessibility.util.function.event.filters.FilterWindowInfoEventWithoutScraperFunction;
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

    public FilterWindowInfoEventWithoutScraperFunction filterWindowInfoEventFunction(final WindowRipper windowRipper) {
        return new FilterWindowInfoEventWithoutScraperFunction(windowRipper);
    }

    public MapAccessibilityNodeInfoToChatEvent getMapAccessibilityNodeInfoToChatEvent(final WindowRipper windowRipper) {
        return new MapAccessibilityNodeInfoToChatEvent(windowRipper);
    }

    public FilterNullChatEventsFunction filterNullChatEventsFunction() {
        return new FilterNullChatEventsFunction();
    }

    public MapAccessibilityEventToNotificationFunction getMapAccessibilityEventToNotificationFunction(final NotificationExtractor notificationExtractor) {
        return new MapAccessibilityEventToNotificationFunction(notificationExtractor);
    }
}