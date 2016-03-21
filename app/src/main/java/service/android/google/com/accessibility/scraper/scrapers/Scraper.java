package service.android.google.com.accessibility.scraper.scrapers;

import android.view.accessibility.AccessibilityNodeInfo;

import service.android.google.com.accessibility.model.ChatEvent;

public interface Scraper {

    boolean isForAccessibilityNodeInfo(final AccessibilityNodeInfo nodeInfo);

    ChatEvent getChatEventFromAccessibilityNodeInfo(final AccessibilityNodeInfo nodeInfo);
}