package service.android.google.com.accessibility.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import service.android.google.com.accessibility.AccessibilityService;
import service.android.google.com.accessibility.dagger.module.AccessibilityModule;

@Singleton
@Component(
        dependencies = {
                Graph.class
        },
        modules = {
                AccessibilityModule.class
        }
)
public interface AccessibilityComponent {
    void inject(AccessibilityService accessibilityService);
}
