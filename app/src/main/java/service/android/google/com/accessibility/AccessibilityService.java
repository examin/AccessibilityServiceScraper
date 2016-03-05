package service.android.google.com.accessibility;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int type = event.getEventType();
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

    @Override
    public void onInterrupt() {
        Log.d(this.getClass().getSimpleName(), "Service was interuppted! ");
    }
}