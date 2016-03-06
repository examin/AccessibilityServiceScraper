package service.android.google.com.accessibility;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import service.android.google.com.accessibility.application.AccessibilityApplication;
import service.android.google.com.accessibility.dagger.component.DaggerAccessibilityComponent;
import service.android.google.com.accessibility.dagger.module.AccessibilityModule;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAccessibilityComponent.builder()
                .graph(AccessibilityApplication.getInstance().graph())
                .accessibilityModule(new AccessibilityModule())
                .build()
                .inject(this);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override
    public void onInterrupt() {
        Log.d(this.getClass().getSimpleName(), "Service was interrupted! ");
    }
}