package service.android.google.com.accessibility.controller;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import service.android.google.com.accessibility.model.ASEvent;

public interface AccessibilityServiceController {

    void evaluateEvent(final AccessibilityNodeInfo rootInActiveWindow, final AccessibilityEvent accessibilityEvent);

    void evaluateEvent(final ASEvent event);

    void handleError(final Throwable e);
}