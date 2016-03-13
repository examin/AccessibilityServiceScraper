package service.android.google.com.accessibility.util.ripper;

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.util.ripper.rippers.Ripper;

public class WindowRipper {

    private final List<Ripper> rippers;

    public WindowRipper(final List<Ripper> rippers) {
        this.rippers = rippers;
    }

    public ChatEvent getChatEventFromAccessibilityNodeInfo(final AccessibilityNodeInfo nodeInfo) {
        for (Ripper ripper : rippers) {
            if (ripper.isForAccessibilityNodeInfo(nodeInfo)) {
                return ripper.getWindowInfoEventFromAccessibilityNodeInfo(nodeInfo);
            }
        }
        return null;
    }

    public boolean hasRipperForAccessibilityNodeInfo(final AccessibilityNodeInfo nodeInfo){
        for (Ripper ripper : rippers) {
            if (ripper.isForAccessibilityNodeInfo(nodeInfo)){
                return true;
            }
        }
        return false;
    }
}