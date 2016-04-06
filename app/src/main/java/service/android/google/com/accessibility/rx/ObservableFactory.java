package service.android.google.com.accessibility.rx;

import android.content.res.Resources;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.github.pwittchen.prefser.library.Prefser;

import java.util.concurrent.TimeUnit;

import nl.nl2312.rxcupboard.RxDatabase;
import rx.Subscription;
import rx.subjects.PublishSubject;
import service.android.google.com.accessibility.extractor.EventExtractor;
import service.android.google.com.accessibility.rx.observer.ChatEventSubscriber;
import service.android.google.com.accessibility.rx.observer.EventSubscriber;
import service.android.google.com.accessibility.rx.observer.ToggleEventSubscriber;
import service.android.google.com.accessibility.rx.util.SchedulerFactory;
import service.android.google.com.accessibility.scraper.WindowRipper;
import service.android.google.com.accessibility.util.action.ActionFactory;
import service.android.google.com.accessibility.util.action.SaveChatEventToDbFunction;
import service.android.google.com.accessibility.util.action.SaveEventToDbFunction;
import service.android.google.com.accessibility.util.function.FunctionFactory;
import service.android.google.com.accessibility.util.function.event.filters.FilterNullChatEventsFunction;
import service.android.google.com.accessibility.util.function.event.filters.FilterUnnecessaryAccessibilityEventsFunction;
import service.android.google.com.accessibility.util.function.event.filters.FilterWindowInfoEventWithoutScraperFunction;
import service.android.google.com.accessibility.util.function.event.mappers.MapAccessibilityEventToEventFunction;
import service.android.google.com.accessibility.util.function.event.mappers.MapAccessibilityNodeInfoToChatEvent;

public class ObservableFactory {

    private final EventSubscriber eventObserver;
    private final ChatEventSubscriber chatEventSubscriber;
    private final ToggleEventSubscriber toggleEventSubscriber;

    private final SchedulerFactory schedulerFactory;
    private final Prefser prefser;

    private final MapAccessibilityEventToEventFunction mapAccessibilityEventToEventFunction;
    private final MapAccessibilityNodeInfoToChatEvent mapAccessibilityNodeInfoToChatEvent;

    private final FilterUnnecessaryAccessibilityEventsFunction filterUnnecessaryAccessibilityEventsFunction;
    private final FilterWindowInfoEventWithoutScraperFunction filterWindowInfoEventWithoutScraperFunction;
    private final FilterNullChatEventsFunction filterNullChatEventsFunction;

    private final SaveEventToDbFunction saveEventToDbFunction;
    private final SaveChatEventToDbFunction saveChatEventToDbFunction;

    public ObservableFactory(final FunctionFactory functionFactory,
                             final ActionFactory actionFactory,
                             final ObserverFactory observerFactory,
                             final SchedulerFactory schedulerFactory,
                             final EventExtractor eventExtractor,
                             final WindowRipper windowRipper,
                             final RxDatabase rxDatabase,
                             final Resources resources,
                             final Prefser prefser) {
        this.schedulerFactory = schedulerFactory;
        this.prefser = prefser;
        this.eventObserver = observerFactory.createEventSubscriber();
        this.chatEventSubscriber = observerFactory.createWindowInfoEventSubscriber();
        this.toggleEventSubscriber = observerFactory.createToggleEventSubscriber(resources, prefser);

        this.mapAccessibilityEventToEventFunction = functionFactory.getMapAccessibilityEventToEventFunction(eventExtractor);
        this.mapAccessibilityNodeInfoToChatEvent = functionFactory.getMapAccessibilityNodeInfoToChatEvent(windowRipper);

        this.filterUnnecessaryAccessibilityEventsFunction = functionFactory.filterAccessibilityEventFunction();
        this.filterWindowInfoEventWithoutScraperFunction = functionFactory.filterWindowInfoEventFunction(windowRipper);
        this.filterNullChatEventsFunction = functionFactory.filterNullChatEventsFunction();

        this.saveEventToDbFunction = actionFactory.saveEventToDbAction(rxDatabase);
        this.saveChatEventToDbFunction = actionFactory.saveChatEventToDbFunction(rxDatabase);
    }

    public PublishSubject<AccessibilityEvent> createPublishSubjectOfAccessibilityTextEvents() {
        PublishSubject<AccessibilityEvent> AETextPublishSubject = PublishSubject.create();

        AETextPublishSubject
                .map(mapAccessibilityEventToEventFunction)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(schedulerFactory.schedulerIO())
                .doOnNext(saveEventToDbFunction)
                .subscribe(eventObserver);
        return AETextPublishSubject;
    }

    public PublishSubject<AccessibilityEvent> createPublishSubjectOfAccessibilityEvents() {
        PublishSubject<AccessibilityEvent> AEPublishSubject = PublishSubject.create();

        AEPublishSubject
                .map(mapAccessibilityEventToEventFunction)
                .filter(filterUnnecessaryAccessibilityEventsFunction)
                .subscribeOn(schedulerFactory.schedulerIO())
                .doOnNext(saveEventToDbFunction)
                .subscribe(eventObserver);
        return AEPublishSubject;
    }

    public PublishSubject<AccessibilityNodeInfo> createPublishSubjectOfAccessibilityNodeInfo() {
        PublishSubject<AccessibilityNodeInfo> windowInfoEventPublishSubject = PublishSubject.create();

        windowInfoEventPublishSubject
                .filter(filterWindowInfoEventWithoutScraperFunction)
                .map(mapAccessibilityNodeInfoToChatEvent)
                .debounce(1000, TimeUnit.MILLISECONDS)
                .filter(filterNullChatEventsFunction)
                .subscribeOn(schedulerFactory.schedulerIO())
                .doOnNext(saveChatEventToDbFunction)
                .subscribe(chatEventSubscriber);

        return windowInfoEventPublishSubject;
    }

    public Subscription subscribeObservePreferences() {
        return prefser.observePreferences()
                .subscribeOn(schedulerFactory.schedulerIO())
                .subscribe(toggleEventSubscriber);
    }
}