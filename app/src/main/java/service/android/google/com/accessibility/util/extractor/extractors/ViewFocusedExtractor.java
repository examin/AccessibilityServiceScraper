package service.android.google.com.accessibility.util.extractor.extractors;

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

        // TODO: 13.03.16 Delete debug purposes
        Event.Builder builder = unfinishedBuilder.itemCount(event.getItemCount());
        Event.Builder builder1 = builder.currentItemIndex(event.getCurrentItemIndex());
        Event.Builder builder2 = builder1.scrollX(event.getScrollX());
        Event.Builder builder3 = builder2.scrollY(event.getScrollY());
        Event.Builder builder4 = builder3.fromIndex(event.getFromIndex());
        Event.Builder builder5 = builder4.toIndex(event.getToIndex());
        return builder5.build();
    }
}