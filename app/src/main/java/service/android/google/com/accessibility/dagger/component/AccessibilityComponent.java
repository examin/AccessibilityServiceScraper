package service.android.google.com.accessibility.dagger.component;

import dagger.Component;
import service.android.google.com.accessibility.dagger.module.AccessibilityModule;

@Component(
        dependencies = {
                Graph.class
        },
        modules = {
                AccessibilityModule.class
        }
)
public interface AccessibilityComponent {
    void inject(android.accessibilityservice.AccessibilityService accessibilityService);
}
