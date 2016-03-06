package service.android.google.com.accessibility.controller;

import android.view.accessibility.AccessibilityEvent;

/**
 * Created by tim on 06.03.16.
 */
public interface AccessibilityServiceController {

    void evaluateEvent(AccessibilityEvent accessibilityEvent);
}
