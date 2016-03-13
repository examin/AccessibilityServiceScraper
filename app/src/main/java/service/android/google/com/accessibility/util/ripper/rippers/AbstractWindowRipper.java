package service.android.google.com.accessibility.util.ripper.rippers;

import android.view.accessibility.AccessibilityNodeInfo;

public abstract class AbstractWindowRipper implements Ripper {

    private final String packageName;

    public AbstractWindowRipper(final String packageName) {
        this.packageName = packageName;
    }

    public String getFQResourceID(final String id) {
        return String.format("%s:id/%s", this.packageName, id);
    }

    @Override
    public boolean isForAccessibilityNodeInfo(AccessibilityNodeInfo nodeInfo) {
        return nodeInfo != null && nodeInfo.getPackageName().equals(packageName);
    }
}