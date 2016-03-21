package service.android.google.com.accessibility.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import java.util.List;

import service.android.google.com.accessibility.model.Event;

/**
 * Created by tim on 09.03.16.
 * represents the event of opening a PopupWindow, Menu, Dialog, etc.
 */
public class WindowStateChangedExtractor extends AbstractEventExtractor {

    private final List<String> ignoredPackages;

    public WindowStateChangedExtractor(final List<String> ignoredPackages) {
        super(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
        this.ignoredPackages = ignoredPackages;
    }

    @Override
    public Event getEventFromAccessibilityEvent(Event.Builder unfinishedBuilder, AccessibilityEvent event) {
        for (String ignoredPackage : ignoredPackages) {
            if (event.getPackageName().equals(ignoredPackage))
                return null;
        }

        return unfinishedBuilder.build();
    }
}
