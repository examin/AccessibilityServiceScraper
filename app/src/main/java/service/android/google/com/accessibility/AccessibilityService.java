package service.android.google.com.accessibility;

import android.view.accessibility.AccessibilityEvent;

import javax.inject.Inject;

import service.android.google.com.accessibility.application.AccessibilityApplication;
import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.dagger.component.DaggerAccessibilityComponent;
import service.android.google.com.accessibility.dagger.module.AccessibilityModule;
import timber.log.Timber;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {

    @Inject
    AccessibilityServiceController accessibilityServiceController;

    @Override
    protected void onServiceConnected() {
        doDaggerInjection();
        super.onServiceConnected();
        Timber.d("AccessibilityService was started!");

    }

    private void doDaggerInjection() {
        DaggerAccessibilityComponent.builder()
                .graph(AccessibilityApplication.getInstance().graph())
                .accessibilityModule(new AccessibilityModule())
                .build()
                .inject(this);
        Timber.d("Dagger injected!");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Timber.d("AccessibilityEvent was received: " + event.toString());
        accessibilityServiceController.evaluateEvent(event);
    }

    @Override
    public void onInterrupt() {
        Timber.e("Service was interrupted! ");
    }
}