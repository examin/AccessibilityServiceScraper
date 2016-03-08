package service.android.google.com.accessibility.controller;

import android.view.accessibility.AccessibilityEvent;

import service.android.google.com.accessibility.model.Event;

/**
 * Created by tim on 06.03.16.
 */
public interface AccessibilityServiceController {

    void evaluateEvent(AccessibilityEvent accessibilityEvent);

    void evaluateEvent(Event event);

    void handleError(Throwable e);
}
