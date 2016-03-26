package service.android.google.com.accessibility;

import android.view.accessibility.AccessibilityEvent;

import javax.inject.Inject;

import service.android.google.com.accessibility.application.AccessibilityApplication;
import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.dagger.component.DaggerAccessibilityComponent;
import service.android.google.com.accessibility.dagger.module.AccessibilityModule;
import service.android.google.com.accessibility.model.ASEvent;
import timber.log.Timber;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService implements AS {

    @Inject
    AccessibilityServiceController controller;

    @Override
    protected void onServiceConnected() {
        doDaggerInjection();
        super.onServiceConnected();
        Timber.d("AccessibilityService was started!");
    }

    private void doDaggerInjection() {
        DaggerAccessibilityComponent.builder()
                .graph(AccessibilityApplication.getInstance().graph())
                .accessibilityModule(new AccessibilityModule(this))
                .build()
                .inject(this);
        Timber.d("Dagger injected!");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Timber.d("AccessibilityEvent was received: " + event.toString());
        controller.evaluateEvent(getRootInActiveWindow(), event);
    }

    @Override
    public void onInterrupt() {
        Timber.e("Service was interrupted! ");
    }

    @Override
    public void evaluateEvent(ASEvent event) {
        controller.evaluateEvent(event);
    }

    @Override
    public void handleError(Throwable e) {
        controller.handleError(e);
    }
}