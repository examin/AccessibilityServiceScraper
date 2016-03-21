package service.android.google.com.accessibility.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import java.util.List;

import service.android.google.com.accessibility.model.Event;

/**
 * Created by tim on 12.03.16.
 * represents the event of focusing a View.
 */
public class ViewFocusedExtractor extends AbstractEventExtractor {

    private final List<String> ignoredPackages;

    public ViewFocusedExtractor(List<String> ignoredPackages) {
        super(AccessibilityEvent.TYPE_VIEW_FOCUSED);
        this.ignoredPackages = ignoredPackages;
    }

    @Override
    public Event getEventFromAccessibilityEvent(Event.Builder unfinishedBuilder, AccessibilityEvent event) {
        for (String ignoredPackage : ignoredPackages) {
            if (event.getPackageName().equals(ignoredPackage))
                return null;
        }

        return unfinishedBuilder.itemCount(event.getItemCount())
                .currentItemIndex(event.getCurrentItemIndex())
                .scrollX(event.getScrollX())
                .scrollY(event.getScrollY())
                .fromIndex(event.getFromIndex())
                .toIndex(event.getToIndex())
                .build();
    }
}