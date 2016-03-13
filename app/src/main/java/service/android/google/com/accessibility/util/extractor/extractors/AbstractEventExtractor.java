package service.android.google.com.accessibility.util.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

public abstract class AbstractEventExtractor implements Extractor {

    private final int accessibilityEventType;

    public AbstractEventExtractor(final int accessibilityEventType) {
        this.accessibilityEventType = accessibilityEventType;
    }

    @Override
    public boolean isForAccessibilityEvent(AccessibilityEvent event) {
        return event.getEventType() == accessibilityEventType;
    }
}