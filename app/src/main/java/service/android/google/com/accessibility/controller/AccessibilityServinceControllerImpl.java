package service.android.google.com.accessibility.controller;

import android.view.accessibility.AccessibilityEvent;

import timber.log.Timber;

public class AccessibilityServinceControllerImpl implements AccessibilityServiceController {

    public AccessibilityServinceControllerImpl() {}

    @Override
    public void evaluateEvent(final AccessibilityEvent accessibilityEvent) {
        int type = accessibilityEvent.getEventType();
        switch (type) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                Timber.d("Event received in controller: " + accessibilityEvent.toString());
                break;
            default:
                break;
        }
    }
}