package service.android.google.com.accessibility.util.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import service.android.google.com.accessibility.model.Event;

public class TypeTextViewExtractor implements Extractor {
    @Override
    public boolean isForAccessibilityEvent(AccessibilityEvent event) {
        return event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED;
    }

    @Override
    public Event getEventFromAccessibilityEvent(Event.Builder unfinishedBuilder, AccessibilityEvent event) {
        return unfinishedBuilder
                .fromIndex(event.getFromIndex())
                .addedCount(event.getAddedCount())
                .removedCount(event.getRemovedCount())
                .beforeText(event.getBeforeText().toString())
                .contentDescription(event.getContentDescription() == null ? null : event.getContentDescription().toString())
                .build();
    }
}