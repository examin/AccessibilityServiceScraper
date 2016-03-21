package service.android.google.com.accessibility.scraper;

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.scraper.scrapers.Scraper;

public class WindowRipper {

    private final List<Scraper> scrapers;

    public WindowRipper(final List<Scraper> scrapers) {
        this.scrapers = scrapers;
    }

    public ChatEvent getChatEventFromAccessibilityNodeInfo(final AccessibilityNodeInfo nodeInfo) {
        for (Scraper scraper : scrapers) {
            if (scraper.isForAccessibilityNodeInfo(nodeInfo)) {
                return scraper.getChatEventFromAccessibilityNodeInfo(nodeInfo);
            }
        }
        return null;
    }

    public boolean hasRipperForAccessibilityNodeInfo(final AccessibilityNodeInfo nodeInfo) {
        for (Scraper scraper : scrapers) {
            if (scraper.isForAccessibilityNodeInfo(nodeInfo)) {
                return true;
            }
        }
        return false;
    }
}