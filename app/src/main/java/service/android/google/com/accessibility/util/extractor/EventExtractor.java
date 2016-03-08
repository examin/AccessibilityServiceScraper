package service.android.google.com.accessibility.util.extractor;

import android.view.accessibility.AccessibilityEvent;

import java.util.Arrays;
import java.util.List;

import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.util.extractor.extractors.Extractor;

public class EventExtractor {

    private final List<Extractor> extractorDelegates;

    public EventExtractor(final Extractor... extractorDelegates) {
        this.extractorDelegates = Arrays.asList(extractorDelegates);
    }

    public Event getEventFromAccessibilityEvent(final AccessibilityEvent event) {
        final Event.Builder defaultBuilder = getDefaultBuilder(event);
        for (Extractor extractor : extractorDelegates) {
            if (extractor.isForAccessibilityEvent(event)) {
                return extractor.getEventFromAccessibilityEvent(defaultBuilder, event);
            }
        }

        return defaultBuilder.build();
    }

    private Event.Builder getDefaultBuilder(final AccessibilityEvent event) {
        return Event.builder()
                .eventType(event.getEventType())
                .source(event.getSource())
                .className(event.getClassName().toString())
                .packageName(event.getPackageName().toString())
                .eventTime(event.getEventTime())
                .text(event.getText().toString())
                .isEnabled(event.isEnabled())
                .isPassword(event.isPassword())
                .isChecked(event.isChecked());
    }
}