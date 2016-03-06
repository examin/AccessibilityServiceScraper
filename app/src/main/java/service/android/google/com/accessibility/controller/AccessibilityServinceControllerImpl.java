package service.android.google.com.accessibility.controller;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by tim on 06.03.16.
 */
public class AccessibilityServinceControllerImpl implements AccessibilityServiceController {

    public AccessibilityServinceControllerImpl() {
    }

    @Override
    public void evaluateEvent(final AccessibilityEvent accessibilityEvent) {
        int type = accessibilityEvent.getEventType();
        switch (type) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                Log.d(this.getClass().getSimpleName(), "Do shit");
                break;
            default:
                break;
        }
    }
}