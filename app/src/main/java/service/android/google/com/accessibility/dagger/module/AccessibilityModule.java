package service.android.google.com.accessibility.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import service.android.google.com.accessibility.AS;
import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.controller.AccessibilityServiceControllerImpl;
import service.android.google.com.accessibility.rx.ObservableFactory;
import service.android.google.com.accessibility.rx.ObserverFactory;
import service.android.google.com.accessibility.util.extractor.EventExtractor;
import service.android.google.com.accessibility.util.extractor.extractors.TypeTextViewExtractor;
import service.android.google.com.accessibility.util.function.FunctionFactory;

@Module
public class AccessibilityModule {

    private AS accessibilityService;

    public AccessibilityModule(AS accessibilityService) {
        this.accessibilityService = accessibilityService;
    }

    @Provides
    AS accessibilityService() {
        return accessibilityService;
    }

    @Provides
    @Singleton
    AccessibilityServiceController accessibilityServiceController(final ObservableFactory observableFactory) {
        return new AccessibilityServiceControllerImpl(observableFactory);
    }

    @Provides
    ObservableFactory observableFactory(final FunctionFactory functionFactory,
                                        final ObserverFactory observerFactory) {
        return new ObservableFactory(functionFactory, observerFactory);
    }

    @Provides
    FunctionFactory functionFactory(final EventExtractor eventExtractor) {
        return new FunctionFactory(eventExtractor);
    }

    @Provides
    EventExtractor eventExtractor() {
        return new EventExtractor(new TypeTextViewExtractor());
    }

    @Provides
    ObserverFactory observerFactory() {
        return new ObserverFactory();
    }
}
