package service.android.google.com.accessibility.util.function.event.mappers;

import android.view.accessibility.AccessibilityNodeInfo;

import rx.functions.Func1;
import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.scraper.WindowRipper;

public class MapAccessibilityNodeInfoToChatEvent implements Func1<AccessibilityNodeInfo, ChatEvent> {
    private final WindowRipper windowRipper;

    public MapAccessibilityNodeInfoToChatEvent(WindowRipper windowRipper) {
        this.windowRipper = windowRipper;
    }

    @Override
    public ChatEvent call(AccessibilityNodeInfo nodeInfo) {
        return windowRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo);
    }
}