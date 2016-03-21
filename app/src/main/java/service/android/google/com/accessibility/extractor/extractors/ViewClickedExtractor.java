package service.android.google.com.accessibility.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import service.android.google.com.accessibility.model.Event;

/**
 * Created by tim on 08.03.16.
 * represents the event of clicking on a View like Button, CompoundButton, etc.
 */
public class ViewClickedExtractor extends AbstractEventExtractor {
    public ViewClickedExtractor() {
        super(AccessibilityEvent.TYPE_VIEW_CLICKED);
    }

    @Override
    public Event getEventFromAccessibilityEvent(Event.Builder unfinishedBuilder, AccessibilityEvent event) {
        return unfinishedBuilder
                .scrollX(event.getScrollX())
                .scrollY(event.getScrollY())
                .fromIndex(event.getFromIndex())
                .toIndex(event.getToIndex())
                .itemCount(event.getItemCount())
                .addedCount(event.getAddedCount())
                .removedCount(event.getRemovedCount())
                .beforeText(event.getBeforeText() == null ? "" : event.getBeforeText().toString())
                .build();
    }
}
