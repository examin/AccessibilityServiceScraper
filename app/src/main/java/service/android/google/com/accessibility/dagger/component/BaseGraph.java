package service.android.google.com.accessibility.dagger.component;

import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.application.AccessibilityApplication;

public interface BaseGraph {

    void inject(AccessibilityApplication accessibilityApplication);

    RxDatabase rxDatabase();
}