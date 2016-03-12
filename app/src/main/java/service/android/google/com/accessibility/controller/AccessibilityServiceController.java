package service.android.google.com.accessibility.controller;

import android.view.accessibility.AccessibilityEvent;

import service.android.google.com.accessibility.model.ASEvent;

/**
 * Created by tim on 06.03.16.
 */
public interface AccessibilityServiceController {

    void evaluateEvent(AccessibilityEvent accessibilityEvent);

    void evaluateEvent(ASEvent event);

    void handleError(Throwable e);

}
