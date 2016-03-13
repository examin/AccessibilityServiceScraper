package service.android.google.com.accessibility.util.ripper.rippers;

import android.view.accessibility.AccessibilityNodeInfo;

import service.android.google.com.accessibility.model.ChatEvent;

public interface Ripper {

    boolean isForAccessibilityNodeInfo(final AccessibilityNodeInfo nodeInfo);

    ChatEvent getWindowInfoEventFromAccessibilityNodeInfo(final AccessibilityNodeInfo nodeInfo);
}