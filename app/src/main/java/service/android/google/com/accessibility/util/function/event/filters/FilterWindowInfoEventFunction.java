package service.android.google.com.accessibility.util.function.event.filters;

import android.view.accessibility.AccessibilityNodeInfo;

import rx.functions.Func1;
import service.android.google.com.accessibility.util.ripper.WindowRipper;

public class FilterWindowInfoEventFunction implements Func1<AccessibilityNodeInfo, Boolean> {

    private final WindowRipper windowRipper;

    public FilterWindowInfoEventFunction(final WindowRipper windowRipper) {
        this.windowRipper = windowRipper;
    }

    @Override
    public Boolean call(AccessibilityNodeInfo nodeInfo) {
        boolean isValid = true;

        isValid = isValid && nodeInfo != null;
        isValid = isValid && windowRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo) != null;

        return isValid;
    }
}