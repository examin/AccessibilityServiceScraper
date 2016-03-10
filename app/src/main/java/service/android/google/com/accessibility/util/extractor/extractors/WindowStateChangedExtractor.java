package service.android.google.com.accessibility.util.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import service.android.google.com.accessibility.model.Event;

/**
 * Created by tim on 09.03.16.
 * represents the event of opening a PopupWindow, Menu, Dialog, etc.
 */
public class WindowStateChangedExtractor implements Extractor {

    @Override
    public boolean isForAccessibilityEvent(AccessibilityEvent event) {
        return event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
    }

    @Override
    public Event getEventFromAccessibilityEvent(Event.Builder unfinishedBuilder, AccessibilityEvent event) {
        return unfinishedBuilder.build();
    }
}
