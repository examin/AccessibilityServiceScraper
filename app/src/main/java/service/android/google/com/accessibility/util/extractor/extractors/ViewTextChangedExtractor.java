package service.android.google.com.accessibility.util.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import service.android.google.com.accessibility.model.Event;

/**
 * Created by tim on 08.03.16.
 * represents the event of changing the text of an EditText.
 */
public class ViewTextChangedExtractor extends AbstractEventExtractor {
    public ViewTextChangedExtractor() {
        super(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
    }

    @Override
    public Event getEventFromAccessibilityEvent(Event.Builder unfinishedBuilder, AccessibilityEvent event) {
        return unfinishedBuilder
                .fromIndex(event.getFromIndex())
                .addedCount(event.getAddedCount())
                .removedCount(event.getRemovedCount())
                .beforeText(event.getBeforeText().toString())
                .build();
    }
}