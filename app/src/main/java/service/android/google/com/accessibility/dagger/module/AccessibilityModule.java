package service.android.google.com.accessibility.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.controller.AccessibilityServinceControllerImpl;

@Module
public class AccessibilityModule {

    public AccessibilityModule() {}

    @Provides
    @Singleton
    AccessibilityServiceController accessibilityServiceController() {
        return new AccessibilityServinceControllerImpl();
    }
}
