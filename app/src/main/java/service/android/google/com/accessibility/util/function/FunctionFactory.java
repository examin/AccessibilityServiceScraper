package service.android.google.com.accessibility.util.function;

import service.android.google.com.accessibility.util.extractor.EventExtractor;
import service.android.google.com.accessibility.util.function.event.MapAccessibilityEventToEventFunction;

/**
 * Created by tim on 08.03.16.
 */
public class FunctionFactory {

    private final EventExtractor eventExtractor;

    public FunctionFactory(final EventExtractor eventExtractor) {
        this.eventExtractor = eventExtractor;
    }

    public MapAccessibilityEventToEventFunction getMapAccessibilityEventToEventFunction() {
        return new MapAccessibilityEventToEventFunction(eventExtractor);
    }
}
