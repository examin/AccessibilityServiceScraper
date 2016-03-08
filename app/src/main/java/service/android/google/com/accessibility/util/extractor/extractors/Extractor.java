package service.android.google.com.accessibility.util.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import service.android.google.com.accessibility.model.Event;

/**
 * Created by tim on 08.03.16.
 */
public interface Extractor {

    boolean isForAccessibilityEvent(final AccessibilityEvent event);

    Event getEventFromAccessibilityEvent(final Event.Builder unfinishedBuilder, final AccessibilityEvent event);
}
